<section class="theory-viewer__block theory-viewer__block_type_markdown">
<div class="Markdown base-markdown base-markdown_with-gallery markdown markdown_size_normal markdown_type_theory full-markdown">
<h1 style="text-align: center;">Тестирование API</h1>
</div>
</section>
<section class="theory-viewer__block theory-viewer__block_type_markdown">
<div class="Markdown base-markdown base-markdown_with-gallery markdown markdown_size_normal markdown_type_theory full-markdown">
<div class="paragraph">Здесь представлен мой дипломный проект по тестированию API по курсу "Автоматизатор тестирования на Java" Яндекс Практикума. Цель: протестировать определенные ручки API для сайта&nbsp;<a href="https://stellarburgers.nomoreparties.site/" target="_blank" rel="noopener">Stellar Burgers</a>&nbsp;с помощью&nbsp;<a href="https://code.s3.yandex.net/qa-automation-engineer/java/cheatsheets/paid-track/diplom/api-documentation.pdf" target="_blank" rel="noopener">документации API</a>.</div>
<div class="paragraph"><strong>Создание пользователя:</strong></div>
<ul>
<li>создать уникального пользователя;</li>
<li>создать пользователя, который уже зарегистрирован;</li>
<li>создать пользователя и не заполнить одно из обязательных полей.</li>
</ul>
<div class="paragraph"><strong>Логин пользователя:</strong></div>
<ul>
<li>логин под существующим пользователем;</li>
<li>логин с неверным логином и паролем.</li>
</ul>
<div class="paragraph"><strong>Изменение данных пользователя:</strong></div>
<ul>
<li>с авторизацией;</li>
<li>без авторизации.</li>
</ul>
<div class="paragraph">Для обеих проверено, что любое поле можно изменить. Для неавторизованного пользователя &mdash; также, что система вернёт ошибку.</div>
<div class="paragraph"><strong>Создание заказа:</strong></div>
<ul>
<li>с авторизацией;</li>
<li>без авторизации;</li>
<li>с ингредиентами;</li>
<li>без ингредиентов;</li>
<li>с неверным хешем ингредиентов.</li>
</ul>
<div class="paragraph"><strong>Получение заказов конкретного пользователя:</strong></div>
<ul>
<li>авторизованный пользователь;</li>
<li>неавторизованный пользователь.</li>
</ul>
</div>
</section>
