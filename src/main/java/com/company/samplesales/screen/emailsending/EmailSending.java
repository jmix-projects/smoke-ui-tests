package com.company.samplesales.screen.emailsending;

import io.jmix.core.Resources;
import io.jmix.email.*;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

@UiController("sales_EmailSending")
@UiDescriptor("email-sending.xml")
public class EmailSending extends Screen {
    public static final String TEST_BCC_EMAIL_ADDRESS = "username@haulmont.com";
    public static final String TEST_CC_EMAIL_ADDRESS = "username@haulmont.com";
    public static final String TEST_TO_EMAIL_ADDRESS = "username@haulmont.com";
    public static final String TEST_FROM_EMAIL_ADDRESS = "username@haulmont.com";
    public static final String EMAIL_BODY_STRING = "Email body";
    public static final String EMAIL_BODY_WITH_MULTI_ATTACHMENT_STRING = "Email body with multiAttachment";
    public static final String EMAIL_ATTACH_PNG = "emailAttach.png";
    public static final String ICO_ID_STRING = "icoId";
    public static final String TEST_ID_STRING = "testId";
    public static final String TEST_PNG_STRING = "test.png";
    public static final String EMAIL_BODY_WITH_SINGLE_ATTACHMENT_STRING = "Email body with single Attachment";

    @Autowired
    private Emailer emailer;
    @Autowired
    private Resources resources;
    @Autowired
    private TextField subject;

    @Subscribe("sync")
    public void onSyncClick(Button.ClickEvent event) throws EmailException, IOException {
        EmailInfo emailInfo = EmailInfoBuilder.create(TEST_TO_EMAIL_ADDRESS,
                        subject.getRawValue(), EMAIL_BODY_STRING)
                .setBcc(TEST_BCC_EMAIL_ADDRESS)
                .setCc(TEST_CC_EMAIL_ADDRESS)
                .build();
        emailer.sendEmail(emailInfo);
    }

    @Subscribe("syncAttach")
    public void onSyncAttachClick(Button.ClickEvent event) throws IOException, EmailException {
        InputStream resourceAsStream = resources.getResourceAsStream(EMAIL_ATTACH_PNG);
        byte[] bytes = IOUtils.toByteArray(resourceAsStream);
        EmailAttachment emailAtt = new EmailAttachment(bytes, EMAIL_ATTACH_PNG, ICO_ID_STRING);
        EmailInfo emailInfo = EmailInfoBuilder.create(TEST_TO_EMAIL_ADDRESS,
                        subject.getRawValue(), EMAIL_BODY_WITH_SINGLE_ATTACHMENT_STRING)
                .setFrom(TEST_FROM_EMAIL_ADDRESS)
                .setBcc(TEST_BCC_EMAIL_ADDRESS)
                .setCc(TEST_CC_EMAIL_ADDRESS)
                .setAttachments(emailAtt)
                .build();
        emailer.sendEmail(emailInfo);
    }

    @Subscribe("syncMultiAttach")
    public void onSyncMultiAttachClick(Button.ClickEvent event) throws IOException, EmailException {
        InputStream resourceAsStream = resources.getResourceAsStream(EMAIL_ATTACH_PNG);
        InputStream resourceAsStream2 = resources.getResourceAsStream(TEST_PNG_STRING);
        byte[] bytes = IOUtils.toByteArray(resourceAsStream);
        byte[] bytes2 = IOUtils.toByteArray(resourceAsStream2);
        EmailAttachment emailAtt = new EmailAttachment(bytes, EMAIL_ATTACH_PNG, ICO_ID_STRING);
        EmailAttachment emailAttTest = new EmailAttachment(bytes2, TEST_PNG_STRING, TEST_ID_STRING);
        EmailInfo emailInfo = EmailInfoBuilder.create(TEST_TO_EMAIL_ADDRESS,
                        subject.getRawValue(), EMAIL_BODY_WITH_MULTI_ATTACHMENT_STRING)
                .setFrom(TEST_FROM_EMAIL_ADDRESS)
                .setBcc(TEST_BCC_EMAIL_ADDRESS)
                .setCc(TEST_CC_EMAIL_ADDRESS)
                .setAttachments(emailAtt, emailAttTest)
                .build();
        emailer.sendEmail(emailInfo);
    }

    @Subscribe("async")
    public void onAsyncClick(Button.ClickEvent event) {
        EmailInfo emailInfo = EmailInfoBuilder.create(TEST_TO_EMAIL_ADDRESS,
                        subject.getRawValue(), EMAIL_BODY_STRING)
                .setFrom(TEST_FROM_EMAIL_ADDRESS)
                .setBcc(TEST_BCC_EMAIL_ADDRESS)
                .setCc(TEST_CC_EMAIL_ADDRESS)
                .build();
        emailer.sendEmailAsync(emailInfo);
    }

    @Subscribe("send")
    public void onSendClick(Button.ClickEvent event) throws EmailException {
        emailer.processQueuedEmails();
    }
}