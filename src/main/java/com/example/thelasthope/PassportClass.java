package com.example.thelasthope;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.component.html.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class PassportClass extends VerticalLayout {

    private MemoryBuffer buffer = new MemoryBuffer();
    private final Image passportImage = new Image();

    public PassportClass() throws IOException {
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
            passportImage.setSrc(streamResource);
            passportImage.setVisible(true);
        });

        VerticalLayout passportLayout = createPassportLayout();

        add(upload, passportImage, passportLayout);
    }

    private VerticalLayout createPassportLayout() throws IOException {
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(Alignment.CENTER); // Center all components horizontally in this layout

        DatePicker.DatePickerI18n i18n = new DatePicker.DatePickerI18n()
                .setToday("Today")
                .setCancel("Cancel")
                .setFirstDayOfWeek(1)
                .setMonthNames(Arrays.asList("January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"))
                .setWeekdays(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday",
                        "Thursday", "Friday", "Saturday"))
                .setWeekdaysShort(Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"));

        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        DatePicker dateOfBirth = new DatePicker("Date of birth");
        dateOfBirth.setI18n(i18n);
        TextField placeOfBirth = new TextField("Place of birth (place, country)");
        TextField nationality = new TextField("Nationality / nationalities");
        ComboBox<String> maritalStatus = new ComboBox<>("Marital status");
        maritalStatus.setItems("Single", "Married", "Divorced", "Widowed");
        maritalStatus.setPlaceholder("Select...");
        ComboBox<String> colourOfEyes = new ComboBox<>("Colour of eyes");
        colourOfEyes.setItems("Blue", "Green", "Gray", "Brown");
        colourOfEyes.setPlaceholder("Select...");
        TextField mobile = new TextField("Mobile (optional)");
        TextField email = new TextField("E-mail (optional)");
        TextField passportNo = new TextField("Passport No");
        DatePicker validFrom = new DatePicker("Valid from");
        validFrom.setI18n(i18n);
        DatePicker validTill = new DatePicker("Valid till");
        validTill.setI18n(i18n);
        TextField issuedBy = new TextField("Issued by");
        TextField issuedOn = new TextField("Issued on");

        RadioButtonGroup<String> gender = new RadioButtonGroup<>();
        gender.setLabel("Sex");
        gender.setItems("Male", "Female", "Diverse");

        layout.add(
                firstName, lastName, dateOfBirth, placeOfBirth, nationality,
                maritalStatus, colourOfEyes, mobile, email, passportNo,
                validFrom, validTill, issuedBy, issuedOn, gender
        );

        return layout;
    }
}
