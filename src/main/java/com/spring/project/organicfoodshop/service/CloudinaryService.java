package com.spring.project.organicfoodshop.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    @Value("${cloudinary.products-folder}")
    private String productsFolder;

    public void handleUploadProductImage(Product product, List<MultipartFile> images) throws IOException {
        Set<ProductImage> productImages = new HashSet<>();
        for (MultipartFile image : images) {
            if (image != null && !image.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("folder", productsFolder));
                ProductImage productImage = new ProductImage();
                productImage.setUrl(uploadResult.get("secure_url").toString());
                productImage.setProduct(product);
                productImages.add(productImage);
            }
        }
        product.getImages().addAll(productImages);
    }

    public void handleRemoveProductImage(Product product) throws IOException {
        for (ProductImage oldImage : product.getImages()) {
            String oldImageUrl = oldImage.getUrl();
            String publicId = oldImageUrl.substring(oldImageUrl.lastIndexOf("/") + 1, oldImageUrl.lastIndexOf("."));
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        }
    }
}
