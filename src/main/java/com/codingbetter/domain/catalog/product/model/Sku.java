package com.codingbetter.domain.catalog.product.model;

import com.codingbetter.domain.shared.model.Entity;
import com.codingbetter.domain.shared.model.Identity;

public class Sku implements Entity<Sku, String> {
    
    private final SkuId id;
    private final String brand;        
    private final String productType;  
    private final String size;         
    private final String color;        
    private final String uniqueId;     
    private final String location;     

    private Sku(SkuBuilder builder) {
        String value = builder.brand + builder.productType + builder.size + builder.color + builder.uniqueId + builder.location;
        this.id = new SkuId(value);
        this.brand = builder.brand;
        this.productType = builder.productType;
        this.size = builder.size;
        this.color = builder.color;
        this.uniqueId = builder.uniqueId;
        this.location = builder.location;
    }

    @Override
    public Identity<String> getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public String getSize() {
        return size;
    }
    
    public String getColor() {
        return color;
    }
    
    public String getUniqueId() {
        return uniqueId;
    }
    
    public String getLocation() {
        return location;
    }

    /**
     * Converts a Sku object to its string representation
     * @param sku The Sku object to convert
     * @return The string representation of the Sku
     */
    public static String toValue(Sku sku) {
        if (sku == null) {
            throw new IllegalArgumentException("Sku cannot be null");
        }
        return sku.getId().getValue();
    }
    
    /**
     * Factory method to create a SKU from a complete SKU string
     * @param skuValue The complete SKU string
     * @return A new Sku instance
     */
    public static Sku fromValue(String skuValue) {
        if (skuValue == null) {
            throw new IllegalArgumentException("Invalid SKU");
        }
        
        if (skuValue.length() != 18) {
            throw new IllegalArgumentException("Invalid SKU");
        }
        
        try {
            String brand = skuValue.substring(0, 3);
            if (!brand.matches("[A-Z]{3}")) {
                throw new IllegalArgumentException("Invalid SKU");
            }
            
            String productType = skuValue.substring(3, 7);
            if (!productType.matches("[A-Z]{4}")) {
                throw new IllegalArgumentException("Invalid SKU");
            }
            
            String size = skuValue.substring(7, 9);
            if (!size.matches("[A-Z]{2}")) {
                throw new IllegalArgumentException("Invalid SKU");
            }
            
            String color = skuValue.substring(9, 12);
            if (!color.matches("[A-Z]{3}")) {
                throw new IllegalArgumentException("Invalid SKU");
            }
            
            String uniqueId = skuValue.substring(12, 15);
            if (!uniqueId.matches("\\d{3}")) {
                throw new IllegalArgumentException("Invalid SKU");
            }
            
            String location = skuValue.substring(15);
            if (!location.matches("\\d{3}")) {
                throw new IllegalArgumentException("Invalid SKU");
            }
            
            return new SkuBuilder()
                    .withBrand(brand)
                    .withProductType(productType)
                    .withSize(size)
                    .withColor(color)
                    .withUniqueId(uniqueId)
                    .withLocation(location)
                    .build();
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid SKU");
        }
    }
    
    public static SkuBuilder builder() {
        return new SkuBuilder();
    }
    
    public static class SkuBuilder {
        private String brand;
        private String productType;
        private String size;
        private String color;
        private String uniqueId;
        private String location;
        
        public SkuBuilder withBrand(String brand) {
            if (brand == null || brand.length() != 3 || !brand.matches("[A-Z]{3}")) {
                throw new IllegalArgumentException("Brand must be 3 uppercase letters");
            }
            this.brand = brand;
            return this;
        }
        
        public SkuBuilder withProductType(String productType) {
            if (productType == null || productType.length() != 4 || !productType.matches("[A-Z]{4}")) {
                throw new IllegalArgumentException("Product type must be 4 uppercase letters");
            }
            this.productType = productType;
            return this;
        }
        
        public SkuBuilder withSize(String size) {
            if (size == null || size.length() != 2 || !size.matches("[A-Z]{2}")) {
                throw new IllegalArgumentException("Size must be 2 uppercase letters");
            }
            this.size = size;
            return this;
        }
        
        public SkuBuilder withColor(String color) {
            if (color == null || color.length() != 3 || !color.matches("[A-Z]{3}")) {
                throw new IllegalArgumentException("Color must be 3 uppercase letters");
            }
            this.color = color;
            return this;
        }
        
        public SkuBuilder withUniqueId(String uniqueId) {
            if (uniqueId == null || uniqueId.length() != 3 || !uniqueId.matches("\\d{3}")) {
                throw new IllegalArgumentException("Unique ID must be 3 digits");
            }
            this.uniqueId = uniqueId;
            return this;
        }
        
        public SkuBuilder withLocation(String location) {
            if (location == null || location.length() != 3 || !location.matches("\\d{3}")) {
                throw new IllegalArgumentException("Location must be 3 digits");
            }
            this.location = location;
            return this;
        }
        
        public Sku build() {
            validateRequiredFields();
            return new Sku(this);
        }
        
        private void validateRequiredFields() {
            if (brand == null) {
                throw new IllegalStateException("Brand is required");
            }
            
            if (productType == null) {
                throw new IllegalStateException("Product type is required");
            }
            
            if (size == null) {
                throw new IllegalStateException("Size is required");
            }
            
            if (color == null) {
                throw new IllegalStateException("Color is required");
            }
            
            if (uniqueId == null) {
                throw new IllegalStateException("Unique ID is required");
            }
            
            if (location == null) {
                throw new IllegalStateException("Location is required");
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Sku sku = (Sku) other;
        return id.equals(sku.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
