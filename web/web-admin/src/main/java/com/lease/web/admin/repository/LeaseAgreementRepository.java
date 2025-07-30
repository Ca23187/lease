package com.lease.web.admin.repository;

import com.lease.model.entity.LeaseAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseAgreementRepository extends JpaRepository<LeaseAgreement, Long> {
//    @Query("SELECT la.apartmentId, COUNT(la.id) " +
//            "FROM LeaseAgreement la " +
//            "WHERE la.apartmentId IN :apartmentIds AND la.status = 'OCCUPIED' " +
//            "GROUP BY la.apartmentId")
//    List<Object[]> countOccupiedRoomsGrouped(@Param("apartmentIds") List<Long> apartmentIds);
//
//    default Map<Long, Long> countOccupiedRoomsByApartmentIds(List<Long> apartmentIds) {
//        return countOccupiedRoomsGrouped(apartmentIds).stream()
//                .collect(Collectors.toMap(
//                        row -> (Long) row[0],
//                        row -> (Long) row[1]
//                ));
//    }

}
