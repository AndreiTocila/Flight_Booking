package com.hcl.bookingservice.controller;

import com.hcl.kafka.dto.AdminFeedback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class MockProducerController
{
    private final KafkaTemplate<Long, AdminFeedback> adminFeedbackKafkaTemplate;

    public MockProducerController(KafkaTemplate<Long, AdminFeedback> adminFeedbackKafkaTemplate)
    {
        this.adminFeedbackKafkaTemplate = adminFeedbackKafkaTemplate;
    }

    @PostMapping("/admin")
    public void produceToAdminFeedback(@RequestParam String bookingId)
    {
        AdminFeedback adminFeedback = new AdminFeedback();
        adminFeedback.setBookingId(bookingId);
        adminFeedback.setStatusValidation(true);
        adminFeedbackKafkaTemplate.send("admin_feedback", 20L, adminFeedback);
    }

    @PostMapping("/payment")
    public void produceToPaymentFeedback(@RequestParam String bookingId)
    {
        AdminFeedback adminFeedback = new AdminFeedback();
        adminFeedback.setBookingId(bookingId);
        adminFeedback.setStatusValidation(true);
        adminFeedbackKafkaTemplate.send("payment_feedback", 20L, adminFeedback);
    }
}
