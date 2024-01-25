package com.example.thelasthope;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.component.html.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SupportClass extends VerticalLayout {

    private VerticalLayout bookLayouts = new VerticalLayout();

    public SupportClass() {
        setAlignItems(FlexComponent.Alignment.CENTER);

        ComboBox<String> bookInfo = new ComboBox<>("Do you get benefits under the Second or Twelfth Book of the Code of Social Law?");
        bookInfo.setItems("Yes", "No");
        bookInfo.setPlaceholder("Select...");

        bookInfo.addValueChangeListener(event -> {
            boolean hasBook = "Yes".equals(event.getValue());
            bookLayouts.setVisible(hasBook);
            if (hasBook) {
                AddingSupportField.benefitsBook(bookLayouts);
            } else {
                bookLayouts.removeAll();
            }
        });

        // Add PDF upload components
        Upload workingContractUpload = createPdfUpload("Working contract PDF");
        Upload blockedAccountUpload = createPdfUpload("Blocked account PDF");
        Upload otherIssuedUpload = createPdfUpload("Other issued PDF");

        // Add other components as needed
        IntegerField textField = new IntegerField("Some Text Field");
        IntegerField datePicker = new IntegerField("Some Date Picker");


        // Create a button to submit the form
        Button submitButton = new Button("Submit");

        // Handle form submission logic here
        submitButton.addClickListener(event -> {
            // Get values from components and process the form
            String selectedBookInfo = bookInfo.getValue();
            // Get values from other components as needed
            // Handle the form submission logic
        });

        // Add all components to the layout
        add(
                bookInfo,
                bookLayouts,
                workingContractUpload,
                blockedAccountUpload,
                otherIssuedUpload,
                textField,
                datePicker,
                submitButton
        );
    }

    private Upload createPdfUpload(String label) {
        FileBuffer buffer = new FileBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/pdf");
        upload.setMaxFiles(1);

        upload.addSucceededListener(event -> {
            InputStream pdfInputStream = buffer.getInputStream();
            StreamResource streamResource = new StreamResource("document.pdf", () -> pdfInputStream);
            streamResource.setContentType("application/pdf");
            // Handle the PDF file as needed, e.g., save it or display it
            // You can use streamResource to display or download the PDF if required
        });

        return upload;
    }
}

