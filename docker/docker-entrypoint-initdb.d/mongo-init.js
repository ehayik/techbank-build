print("Started Adding the Users.");

db = db.getSiblingDB("admin");

db.createUser({
  user: "userx",
  pwd: "1234",
  roles: [{ role: "readWrite", db: "bankAccount" }],
});

print("End Adding the User Roles.");