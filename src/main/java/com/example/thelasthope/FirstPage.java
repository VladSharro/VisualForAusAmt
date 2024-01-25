package com.example.thelasthope;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("") // Пустая строка указывает на начальную страницу
public class FirstPage extends VerticalLayout {

    public FirstPage() { // Конструктор должен иметь то же имя, что и класс

        setAlignItems(Alignment.CENTER); // Центрирует все компоненты по горизонтали

        // Заголовок страницы
        H1 title = new H1("Residence Permit Application Portal");

        // Кнопка для начала нового заявления
        Button startApplication = new Button("Start Application");
        startApplication.addClickListener(e -> {
            startApplication.getUI().ifPresent(ui -> ui.navigate("startiew"));
            // логика для начала нового заявления
        });

        // Параграф с информацией
        Paragraph info = new Paragraph("You can apply for a student residence permit, both first-time and extension, through this service. We'll take you through the application step by step.");

        // Кнопка для продолжения заявления
        Button continueApplication = new Button("Continue Application");
        continueApplication.addClickListener(e -> {
            // логика для продолжения существующего заявления
        });

        // Добавление компонентов на макет
        add(title, info, startApplication, continueApplication);
    }
}
