package com.AutomationExercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageData implements IObjectFeatures {

    private String name;
    String format;
    String base64Image;

    String imageURL =null;

    private static final Logger logger = LoggerFactory.getLogger(TestCase.class);

    public ImageData(String _name,String _format, String _imageURL){
        name =_name;
        format = _format;
        imageURL = _imageURL;

    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    public String getFormat(){
        return format;
    }
    public void setFormat(String format){
        this.format = format;
    }
    public String getImageURL(){
        return imageURL;
    }
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    /**
     * Verilen URL üzerinden bir resmi indirir ve resmi Base64 formatına çevirir.
     *
     * Bu metod, belirli bir URL'den bir resim indirir, resmi JPEG formatında sıkıştırır ve ardından
     * bu resmi Base64 formatına çevirerek `base64Image` değişkenine atar.
     *
     * @throws IOException Eğer ağ hatası, resim okuma hatası veya yazma hatası oluşursa
     * @throws IllegalArgumentException Eğer geçersiz bir URL veya başka bir geçersiz giriş sağlanırsa
     */
    public void EcodeBase64ToImage() throws IOException {

        BufferedImage image = null;
        try {
            //Url üzerinden resmi indir
            image = ImageIO.read(new URL(imageURL));

            //base64 e çevir
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
            JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);

            // Sıkıştırma modunu ayarla
            jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT); // Sıkıştırma modunu "explicit" yapıyoruz


            // Kaliteyi ayarlıyoruz (0.0 - 1.0 arası, 0.5 = %50 kalite)
            jpegParams.setCompressionQuality(0.5f);  // Buradaki 0.5'i düşük kaliteye göre ayarlayın


            writer.setOutput(ImageIO.createImageOutputStream(baos));
            writer.write(null, new javax.imageio.IIOImage(image, null, null), jpegParams);

          //  ImageIO.write(image, "jpg",baos);
            byte[] imageByte = baos.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageByte);

        }
        catch (SocketException e) {
            logger.error("Network error: ", e.getMessage());
        } catch (IOException e) {
            logger.error("Error reading the image: ", e.getMessage());
            System.err.println("Error reading the image: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Network error: ", e.getMessage());
            System.err.println(e.getMessage());
        }




    }

    /**
     * Base64 kodlu bir string'i BufferedImage nesnesine dönüştürür.
     *
     * Bu metod, bir Base64 kodlamalı string'i çözer ve onu bir resim (BufferedImage) nesnesine dönüştürür.
     * Base64 kodlamalı bir resmi byte dizisine çevirir ve ardından bu byte dizisini bir `BufferedImage` nesnesine dönüştürür.
     *
     * @param base64String Type: String Info: Base64 formatında kodlanmış resim verisini içeren string
     * @return Type: BufferedImage Info: Base64 string'inden dönüştürülmüş resim
     * @throws IOException Eğer Base64 string'i çözerken veya resmi okurken bir hata oluşursa
     */
    public static BufferedImage decodeBase64ToImage(String base64String) throws IOException {
        // Base64 string'i byte dizisine dönüştür
        byte[] imageBytes = Base64.getDecoder().decode(base64String);

        // Byte dizisini BufferedImage'e çevir
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
        return ImageIO.read(byteArrayInputStream);
    }


}
