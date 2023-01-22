# CodeName/Кодовые имена

## Описание
Это приложениe - адаптация настольной игры кодовые имена. Мое первое приложение на kotlin. 

Правила игры : https://tesera.ru/images/items/657300/codenames_rules_ru_1_5.pdf. Адаптация ничем не отличается по правилам от настольной игры. 
В него можно играть на одном телефоне так и на нескольких. Возможен выбор языка - русский или английский. Имеется большая база данных слов - английских и русских.
Также имеется возможность играть на поле 5x6, помимо алгоритма 5 на 5. 

##Алгоритм генерации поля
В игре используется встроенный набор полей. Из четырехзначного числа - кода берутся параметры как остатки от деления. Эти параметры определяют выбранное поле из базы данных на телефоне, инверсию(то слов какой команды больше, красной или синей и другие параметры). 
Благодаря этому алгоритму между телефоном команды капитанов и команды игроков не нужно интернет-соединение. 

## Общие сведения
В данном приложении использовались различные графические эффекты. Есть эффект появления поля на экране. Также для кнопок и фона применены градиенты - имеются свои xml файлы описания их вида. 
В приложении имеется звук - при нажатии на клавиши или победе той или иной команды. Приложение реализовано через Activity. Данные между Activity передаются через Intent.
Используются кастомные тулбары. С помощью тулбара можно переключаться между экранами приложения и заходить в настройки. 

### Экраны приложения 
Начальный экран и правила
<img src="https://i.ibb.co/N9BRxPj/Code-Names1.png" height=400/>

Экран выбора команд, капитаны/игроки. Экраны генерации ввода ключа.
Игроки на своем телефоне генерируют ключ, этот ключ капитаны вводят на своем телефоне. 
<img src="https://i.ibb.co/bsDLXtf/Code-Names2.png" height=400/>
Экран поля капитанов игроков
С помощью кнопки Show field возможна игра на одном телефоне. Так капитаны могут взять телефон, посмотреть принадлежащие им слова, выключить отображение не раскрытых слов и передать телефон игрокам. 
<img src="https://i.ibb.co/zJj2FZJ/Code-Names3.png" height=400/>
