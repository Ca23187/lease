package com.lease.web.admin.schedule;

import com.lease.web.admin.repository.LeaseAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {

    @Autowired
    private LeaseAgreementRepository repository;

    @Scheduled(cron = "0 0 0 * * *")
    public void checkLeaseStatus() {
        repository.updateExpiredAgreements(new Date());
    }
}