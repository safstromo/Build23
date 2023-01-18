package org.example.testable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

//@SuppressWarnings({"unchecked"})
@ExtendWith(MockitoExtension.class)
class TestabilityTest {

    LoggerImpl logger = new LoggerImpl();  //Use our own test double

    MailSender mailSender = mock(MailSender.class); //Use mockito to mock mailsender

    @Mock
    List<String> strings;// = Mockito.mock(List.class);

    Testability testability = new Testability(logger, mailSender);

    @Test
    void runWithValidEmailShouldLogAndSendMail() {
        testability.run();

        assertTrue(logger.logIsCalled);

        Mockito.verify(mailSender, times(1)).sendMail(eq("some-invalid-email-address.com"), anyString());
    }

    @Test
    void runWithInvalidEmailShouldLogMessageWithErrorLevel() {
        doThrow(new IllegalArgumentException()).when(mailSender).sendMail(anyString(),anyString());

        testability.run();

        assertEquals(2, logger.count);
    }


}

