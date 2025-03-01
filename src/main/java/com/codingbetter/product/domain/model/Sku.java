package com.codingbetter.product.domain.model;

public class Sku {

    // Format: [Brand(3)][ProductType(4)][Size(2)][Color(3)][ID(3)][Location(3)]
    public static final String REGEX_SKU = "^[A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}$";
    
    private final String value;
    private final String brand;        
    private final String productType;  
    private final String size;         
    private final String color;        
    private final String uniqueId;     
    private final String location;     

    private Sku(SkuBuilder builder) {
        this.value = builder.brand + builder.productType + builder.size + builder.color + builder.uniqueId + builder.location;
        this.brand = builder.brand;
        this.productType = builder.productType;
        this.size = builder.size;
        this.color = builder.color;
        this.uniqueId = builder.uniqueId;
        this.location = builder.location;
    }

     public String getValue() {
        return value;
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
        return sku.getValue();
    }
    
    /**
     * Factory method to create a SKU from a complete SKU string
     * @param skuValue The complete SKU string
     * @return A new Sku instance
     */
    public static Sku fromValue(String skuValue) {
        if (skuValue == null || !skuValue.matches(REGEX_SKU)) {
            throw new IllegalArgumentException("Invalid SKU. Expected format: [Brand(3)][ProductType(4)][Size(2)][Color(3)][ID(3)][Location(3)]. Example: NIKSHRTLGBLUE001351");
        }
        
        int currentIndex = 0;
        String brand = skuValue.substring(currentIndex, currentIndex + 3);
        currentIndex += 3;
        
        String productType = skuValue.substring(currentIndex, currentIndex + 4);
        currentIndex += 4;
        
        String size = skuValue.substring(currentIndex, currentIndex + 2);
        currentIndex += 2;
        
        String color = skuValue.substring(currentIndex, currentIndex + 3);
        currentIndex += 3;
        
        String uniqueId = skuValue.substring(currentIndex, currentIndex + 3);
        currentIndex += 3;
        
        String location = skuValue.substring(currentIndex);
        
        return new SkuBuilder()
                .withBrand(brand)
                .withProductType(productType)
                .withSize(size)
                .withColor(color)
                .withUniqueId(uniqueId)
                .withLocation(location)
                .build();
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
}
