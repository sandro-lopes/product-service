db = db.getSiblingDB('productdb');

db.createUser({
  user: 'admin',
  pwd: 'password',
  roles: [
    { role: 'readWrite', db: 'productdb' },
    { role: 'dbAdmin', db: 'productdb' }
  ]
});

// Create products collection
db.createCollection('products');

// Create unique index on sku field
db.products.createIndex({ "sku": 1 }, { unique: true });

print('MongoDB initialization completed successfully!'); 