package com.riko.setup.service.implementation;

import com.riko.setup.client.service.TheatreClientService;
import com.riko.setup.model.SeatSetup;
import com.riko.setup.model.dto.SeatSetupMinDTO;
import com.riko.setup.model.enums.SeatType;
import com.riko.setup.repo.SeatSetupRepository;
import com.riko.setup.service.SeatSetupService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SeatSetupServiceImpl implements SeatSetupService {

    private final SeatSetupRepository seatSetupRepository;
    private final TheatreClientService theatreClientService;
    private final EntityManager em;

    @Override
    public List<SeatSetup> getAllSeats() {
        return seatSetupRepository.findAll();
    }

    @Override
    public SeatSetup createSeatSetup(SeatSetupMinDTO seatSetupMinDTO) {
        theatreClientService.checkTheatre(seatSetupMinDTO.getTheatreSeatTypeId().getTheatreId());
        return seatSetupRepository.save(SeatSetup.builder()
                .theatreSeatType(seatSetupMinDTO.getTheatreSeatTypeId())
                .rowCount(seatSetupMinDTO.getRowCount())
                .seatCount(seatSetupMinDTO.getSeatCount())
                .seatCost(seatSetupMinDTO.getSeatCost())
                .build());
    }

    @Override
    public List<SeatSetup> getSeatSetupByTheatreId(Long theatreId) {
        return seatSetupRepository.findByTheatreSeatTypeTheatreId(theatreId);
    }

    @Override
    public List<SeatSetup> getSeatSetupBySeatType(SeatType seatType) {
        return seatSetupRepository.findByTheatreSeatTypeSeatType(seatType);
    }

    @Override
    public List<SeatSetup> getSeatSetupByTheatreIdAndSeatType(Long theatreId, SeatType seatType) {
        return seatSetupRepository.findByTheatreSeatTypeTheatreIdAndTheatreSeatTypeSeatType(theatreId, seatType);
    }

    @Override
    public SeatSetup getSeatSetupByTheatreIdAndSeatTypeAndHallName(Long theatreId, SeatType seatType, String hallName) {
        return seatSetupRepository
                .findByTheatreSeatTypeTheatreIdAndTheatreSeatTypeSeatTypeAndTheatreSeatTypeHallName
                        (theatreId, seatType, hallName)
                .orElseThrow(() -> new EntityNotFoundException(
                        "seat not found with"
                                + " theatreId: " + theatreId
                                + " seatType: " + seatType
                                + " hallName: " + hallName));
    }

    @Override
    public List<SeatSetup> searchSeatSetupByTheatreIdAndSeatTypeAndHallName(Long theatreId, SeatType seatType, String hallNameKey) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SeatSetup> cq = cb.createQuery(SeatSetup.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<SeatSetup> seatSetup = cq.from(SeatSetup.class);

        if (theatreId != null) {
            predicates.add(cb.equal(seatSetup.get("theatreSeatType").get("theatreId"), theatreId));
        }

        if (seatType != null) {
            predicates.add(cb.equal(seatSetup.get("theatreSeatType").get("seatType"), seatType));
        }

        if (hallNameKey != null && !hallNameKey.isEmpty()) {
            predicates.add(cb.like(seatSetup.get("theatreSeatType").get("hallName"), "%" + hallNameKey + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        List<SeatSetup> seatSetups = em.createQuery(cq).getResultList();
        return seatSetups;
    }
}
