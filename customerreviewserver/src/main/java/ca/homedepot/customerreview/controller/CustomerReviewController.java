package ca.homedepot.customerreview.controller;

import java.util.*;
import ca.homedepot.customerreview.dao.ProductDao;
import ca.homedepot.customerreview.dao.UserDao;
import ca.homedepot.customerreview.exception.ProductNotFoundException;
import ca.homedepot.customerreview.exception.UserNotFoundException;

import ca.homedepot.customerreview.exception.negativeRatingException;
import ca.homedepot.customerreview.exception.curseWordException;
import ca.homedepot.customerreview.exception.duplicateCurseWordException;
import ca.homedepot.customerreview.exception.curseWordNotExistException;
import ca.homedepot.customerreview.exception.ratingEnterException;





import ca.homedepot.customerreview.forms.CustomerReviewForm;
import ca.homedepot.customerreview.model.CustomerReviewModel;
import ca.homedepot.customerreview.model.ProductModel;
import ca.homedepot.customerreview.model.UserModel;
import ca.homedepot.customerreview.service.CustomerReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import java.lang.Math;

import java.util.List;


@RestController
public class CustomerReviewController{
    
    private ArrayList<String> Badwords = new ArrayList<String>();
    
    public CustomerReviewController(){
        Badwords.add("Ship".toLowerCase());
        Badwords.add("Miss".toLowerCase());
        Badwords.add("Duck".toLowerCase());
        Badwords.add("Punt".toLowerCase());
        Badwords.add("Rooster".toLowerCase());
        Badwords.add("Mother".toLowerCase());
        Badwords.add("Bits".toLowerCase());
    }
    
    public void addBadWords(String s){
        if (! Badwords.contains(s.toLowerCase())){
            Badwords.add(s.toLowerCase());
        }else{
            throw new duplicateCurseWordException(s);
        }
    } 
    
    public void deleteBadWords(String s){
        if (! Badwords.contains(s.toLowerCase())){
            throw new curseWordNotExistException(s);
        }else{
            Badwords.remove(s.toLowerCase());
        } 
    }
    
    
    
    
    @Autowired
	private ProductDao productDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CustomerReviewService customerReviewService;

	@GetMapping({ "products/{productId:\\d+}/reviews" })
	public List<CustomerReviewModel> getReviews(@PathVariable final Long productId,
			@RequestParam(required = false) final Double ratingFrom, @RequestParam(required = false) final                           Double ratingTo)
	{
		final ProductModel product = productDao.findOne(productId);
        
        
        
		if (product == null)
		{
			throw new ProductNotFoundException(productId);
		}
        
        if (ratingFrom == null && ratingTo == null ){
            return customerReviewService.getReviewsForProduct(product);
        }
        
        if (ratingFrom == null || ratingTo == null){
            throw new ratingEnterException();
        }
        
      
        
        Double lower = Math.min(ratingFrom, ratingTo);
        Double upper = Math.max(ratingFrom, ratingTo) ;
        
        
        //here I assume that getReviewsForProduct is already implemented correctly...
        //Here the double Lists is potentially causing overheads to the extent of both Memory as well as           //Runtime !
        //but I dunno where should I implement a function like getReviewsForProduct or am I 
        //allowed to change the function parameters
        
		List<CustomerReviewModel> temp =  customerReviewService.getReviewsForProduct(product);
         
        List<CustomerReviewModel> result = new ArrayList<CustomerReviewModel>();
        
        for (CustomerReviewModel c: temp){
            if(c.getRating() >= lower && c.getRating() <= upper ){
                result.add(c);
            }
        }
        
        return result;
        
        
	}
    


	@PostMapping({ "products/{productId:\\d+}/users/{userId:\\d+}/reviews" })
	public CustomerReviewModel createReview(@PathVariable final Long userId, @PathVariable final Long                              productId,
			@RequestBody final CustomerReviewForm customerReviewForm)
	{
		final ProductModel product = productDao.findOne(productId);
		if (product == null)
		{
			throw new ProductNotFoundException(productId);
		}

		final UserModel user = userDao.findOne(userId);
		if (user == null)
		{
			throw new UserNotFoundException(userId);
		}
         
        
        for (String s : Badwords){
            if (customerReviewForm.getComment().toLowerCase().indexOf(s.toLowerCase()) != -1 ||
               customerReviewForm.getHeadline().toLowerCase().indexOf(s.toLowerCase()) != -1)
               {
                throw new curseWordException(s);
            }
            
        }
        
        if (customerReviewForm.getRating() < 0){
            throw new negativeRatingException();
        }
        
        
		return customerReviewService
				.createCustomerReview(customerReviewForm.getRating(), customerReviewForm.getHeadline(),
						customerReviewForm.getComment(), product, user);
	}

	@PostMapping({ "products" })
	public ProductModel createProduct()
	{
		final ProductModel product = new ProductModel();
		productDao.save(product);
		return product;
	}

	@PostMapping({ "users" })
	public UserModel createUser()
	{
		final UserModel user = new UserModel();
		userDao.save(user);
		return user;
	}

	@DeleteMapping({ "reviews/{reviewId:\\d+}" })
	public void deleteReview(@PathVariable final Long reviewId)
	{
		customerReviewService.deleteCustomerReview(reviewId);
	}
    
 
}




