/*CREATE TABLE "worker"(
                       "id" SERIAL PRIMARY KEY,
                       "name" TEXT DEFAULT ' ' NOT NULL,
                       "post" TEXT DEFAULT ' ' NOT NULL,
                       "phone_number" TEXT DEFAULT ' ' NOT NULL
);
CREATE TABLE "vehicle"(
                        "id" SERIAL PRIMARY KEY,
                        "brand_of_transport" TEXT DEFAULT ' ' NOT NULL,
                        "model" TEXT DEFAULT ' ' NOT NULL,
                        "state_number" TEXT DEFAULT ' ' NOT NULL,
                        "color" TEXT DEFAULT ' ' NOT NULL,
                        "worker_id" INT REFERENCES "worker" ON DELETE SET NULL
);
CREATE TABLE "academic_building"(
                                  "id" SERIAL PRIMARY KEY,
                                  "name" TEXT DEFAULT ' ' NOT NULL,
                                  "address" TEXT DEFAULT ' ' NOT NULL
);
CREATE TABLE "admission"(
                          "id" SERIAL PRIMARY KEY,
                          "academic_building_id" INT REFERENCES "academic_building" ON DELETE SET NULL,
                          "vehicle_id" INT REFERENCES "vehicle" ON DELETE SET NULL,
                          "term" TIMESTAMP DEFAULT ' ' NOT NULL
);
*/