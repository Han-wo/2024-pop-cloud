package com.domain.show.popups.domain.response;

import com.domain.show.common.PublicTag;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PopupsSpecificResponse(
        Long popupsId,
        Long ownerId,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String openTimes,
        String location,
        BigDecimal latitude,
        BigDecimal longitude,
        Boolean isParkingAvailable,
        Boolean isFoodAllowed,
        Boolean isPetAllowed,
        Boolean isKidsZone,
        Boolean isWifiAvailable,
        BigDecimal fee,
        String publicTag,
        Integer visitedCount,
        Integer likedCount,
        List<String> tags
) {

    public PopupsSpecificResponse(
            Long popupsId,
            Long ownerId,
            String title,
            String description,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String openTimes,
            String location,
            BigDecimal latitude,
            BigDecimal longitude,
            Boolean isParkingAvailable,
            Boolean isFoodAllowed,
            Boolean isPetAllowed,
            Boolean isKidsZone,
            Boolean isWifiAvailable,
            BigDecimal fee,
            PublicTag publicTag,
            Integer visitedCount,
            Integer likedCount,
            List<String> tags
    ) {
        this(
                popupsId,
                ownerId,
                title,
                description,
                startDate,
                endDate,
                openTimes,
                location,
                latitude,
                longitude,
                isParkingAvailable,
                isFoodAllowed,
                isPetAllowed,
                isKidsZone,
                isWifiAvailable,
                fee,
                publicTag.getName(),
                visitedCount,
                likedCount,
                tags
        );
    }
}
