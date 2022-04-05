db.createUser(
    {
        user: "storeAdmin",
        pwd: "storeAdmin2022",
        roles: [
            {
                role: "readWrite",
                db: "bankAccount"
            }
        ]
    }
)