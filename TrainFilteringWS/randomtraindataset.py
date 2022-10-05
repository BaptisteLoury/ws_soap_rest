import random

print("INSERT INTO TRAINS (TRAIN_NAME, TRAIN_SOURCE, TRAIN_DESTINATION, TRAIN_DEPARTURE_TIME, TRAIN_ARRIVAL_TIME, TRAIN_DATE, TRAIN_PRICE, TRAIN_AVAILABILITY) VALUES")
for i in range(25):
    # random date string
    date = "2022-"
    month = str(random.randint(1,12))
    if len(month) == 1:
        month = "0" + month
    date += month + "-"
    day = str(random.randint(1,28))
    if len(day) == 1:
        day = "0" + day
    date += day


    source = random.choice(["Paris", "Lyon", "Marseille", "Bordeaux", "Lille"])
    destination = random.choice(["Paris", "Lyon", "Marseille", "Bordeaux", "Lille"])
    while source == destination:
        destination = random.choice(["Paris", "Lyon", "Marseille", "Bordeaux", "Lille"])
    print("('{}', '{}', '{}', '{}', '{}', '{}', {}, {}),".format(
        random.choice(["Express", "TGV", "TER", "Thalys"]),
        source,
        destination,
        random.choice(["08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"]),
        random.choice(["08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"]),
        date,
        random.randint(50,150),
        random.randint(10,200)
    ))
