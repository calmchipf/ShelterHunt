SE-2305 - Группа №1:
Досымжан Сатыбалды
Азамат Сайлаубек
Шынгыс Есенбай

Агентство недвижимости. Можно будет просматривать объявления (фото, описания, отзывы), фильтровать их по желанию и находить то, что интересует.
В базе будут храниться адреса, фото, цены, описания, отзывы, закладки для интересуемой недвижимости, данные пользователей по типу их объявлений, контактов и т.д

ShelterHunt - name of the project and the group

Table users:
id (serial) Primary Unique
name (char var 64)
surname (char var 64)
gender (boolean)
date_of_birth (date, 'dd.mm.yyyy')
phone_number (int) Unique
owned_adverts_ids (int[]) Unique
fav_adverts_ids (int[]) Unique
review_ids (int[]) Unique 

The Table adverts:
id (serial) Primary Unique
address (char var 256)
location (char var 256)
price (int)
description (char var 1024)
photos_ids (int[]) Unique 
review_ids (int[]) Unique 

The Table photos:
id (serial) Primary Unique
link (char var 512)
