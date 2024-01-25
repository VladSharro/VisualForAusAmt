package com.example.thelasthope;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class InsuranceClass extends VerticalLayout {

    private MemoryBuffer buffer = new MemoryBuffer();
    private final Image insuranceImage = new Image();

    public InsuranceClass() throws IOException {
        setAlignItems(Alignment.CENTER); // Center all components horizontally in this layout

        Upload upload = new Upload(buffer);
        upload.setMaxFiles(1); // Максимальное количество загружаемых файлов
        upload.setAcceptedFileTypes("image/*"); // Разрешенные типы файлов (только изображения)

        upload.addSucceededListener(event -> {
            StreamResource streamResource = new StreamResource("passport-image.png", () -> {
                byte[] imageData = new byte[0];
                try {
                    imageData = buffer.getInputStream().readAllBytes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return new ByteArrayInputStream(imageData);
            });
            insuranceImage.setSrc(streamResource);
            insuranceImage.setVisible(true);
        });

        // Создаем макет с полями для ввода данных паспорта
        VerticalLayout passportLayout = createInsuranceLayout();

        // Добавляем компоненты на макет
        add(upload, insuranceImage, passportLayout);
    }

    private VerticalLayout createInsuranceLayout() throws IOException {
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(Alignment.CENTER); // Center all components horizontally in this layout

        TextField insurerCompanyName = new TextField("Company of insurance");
        DatePicker dateOfEnd = new DatePicker("Date when you unsurance working");

        layout.add(
                insurerCompanyName, dateOfEnd
        );

        return layout;

    }
}
