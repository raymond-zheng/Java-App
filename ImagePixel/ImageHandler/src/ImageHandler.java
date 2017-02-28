import java.io.*;
import edu.duke.*;

public class ImageHandler {

		//creating a new method for the class GreyScale
	    public void makeGreyScale(){
	        //prompt to ask user to select files from the directory
	        DirectoryResource dr = new DirectoryResource();
	        //loop over the images that user selects
	        for(File f : dr.selectedFiles()){
	            //here grayimage is the new same-size blank image of the original one
	            ImageResource img = new ImageResource(f);
	            //ImageResource grayimage = new ImageResource(img.getWidth(),img.getHeight());
	            //loop over each pixel of the newly-created blank image
	            for(Pixel pixel : img.pixels()){
	                //locate the corresponding pixel of the original image 
	                Pixel current = img.getPixel(pixel.getX(), pixel.getY());
	                //calculate the average of blue, red, green of the original image's pixel
	                int avg = (current.getBlue() + current.getRed() + current.getGreen())/3;
	                //set the pixel(belongs to grayimage) of red to avg
	                pixel.setRed(avg);
	                //set the pixel(belongs to grayimage) of green to avg
	                pixel.setGreen(avg);
	                //set the pixel(belongs to grayimage) of blue to avg
	                pixel.setBlue(avg);
	            }
	            //set newName a string variable that represents the file name of grayimage
	            String newName = "gray-" + img.getFileName();
	            //set the file name as newName
	            img.setFileName(newName);
	            //draw and save the image
	            img.draw();
	            img.save();
	            
	        }
	    }
	    
	    
	    public void InvertColor(){
	        DirectoryResource dir = new DirectoryResource();
	        for(File f: dir.selectedFiles()){
	            ImageResource curImage = new ImageResource(f);
	            for(Pixel pixel : curImage.pixels()){
	                Pixel curPixel = curImage.getPixel(pixel.getX(),pixel.getY());
	                int newBlue = 255 - curPixel.getBlue();
	                int newGreen = 255 - curPixel.getGreen();
	                int newRed = 255 - curPixel.getRed();
	                pixel.setRed(newRed);
	                pixel.setGreen(newGreen);
	                pixel.setBlue(newBlue);
	      
	            }
	            String newName = "inverted-" + curImage.getFileName();
	            curImage.setFileName(newName);
	            curImage.draw();
	            curImage.save();
	        }
	    }
	    
	    //For testing purposes only
	    public void TesterForSavingImages(){
	        DirectoryResource dir = new DirectoryResource();
	        for(File f : dir.selectedFiles()){
	            ImageResource temp = new ImageResource(f);
	            String name = temp.getFileName();
	            String newName = "duplicate-" + name;
	            temp.setFileName(newName);
	            temp.draw();
	            temp.save();
	    }
	    }
	    
	    //The code below is for developer use only, and the function implementation does not work properly
	    /*
	    public void makeGreyScale(){
	        //prompt to ask user to select files from the directory
	        DirectoryResource dr = new DirectoryResource();
	        //loop over the images that user selects
	        for(File f : dr.selectedFiles()){
	            //here grayimage is the new same-size blank image of the original one
	            ImageResource img = new ImageResource(f);
	            ImageResource grayimage = new ImageResource(img.getWidth(),img.getHeight());
	            //loop over each pixel of the newly-created blank image
	            for(Pixel pixel : greyimage.pixels()){
	                //locate the corresponding pixel of the original image 
	                Pixel current = img.getPixel(pixel.getX(), pixel.getY());
	                //calculate the average of blue, red, green of the original image's pixel
	                int avg = (current.getBlue() + current.getRed() + current.getGreen())/3;
	                //set the pixel(belongs to grayimage) of red to avg
	                pixel.setRed(avg);
	                //set the pixel(belongs to grayimage) of green to avg
	                pixel.setGreen(avg);
	                //set the pixel(belongs to grayimage) of blue to avg
	                pixel.setBlue(avg);
	            }
	            //set newName a string variable that represents the file name of grayimage
	            String newName = "gray-" + img.getFileName();
	            //set the file name as newName
	            grayimage.setFileName(newName);
	            //draw and save the image
	            grayimage.draw();
	            grayimage.save();
	            
	        }
	    }
	    */
	}


