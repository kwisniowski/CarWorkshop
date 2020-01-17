package com.projects.carworkshop.mapper;

import com.projects.carworkshop.domain.ApplicationEvent;
import com.projects.carworkshop.dto.ApplicationEventDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationEventMapper {

    public ApplicationEvent mapToApplicationEvent (ApplicationEventDto appEventDto) {
        return new ApplicationEvent(appEventDto.getId(), appEventDto.getType(), appEventDto.getDate(), appEventDto.getTime(),
                appEventDto.getInfo());
    }

    public ApplicationEventDto mapToApplicationEventDto (ApplicationEvent appEvent) {
        return new ApplicationEventDto(appEvent.getId(), appEvent.getType(), appEvent.getDate(), appEvent.getTime(),
                appEvent.getInfo());
    }

    public List<ApplicationEvent> mapToApplicationEventList(List<ApplicationEventDto> applicationEventDtos) {
        return applicationEventDtos.stream()
                .map(appEventDto -> new ApplicationEvent(appEventDto.getId(), appEventDto.getType(), appEventDto.getDate(),
                        appEventDto.getTime(),appEventDto.getInfo()))
                .collect(Collectors.toList());
    }

    public List<ApplicationEventDto> mapToApplicationEventDtoList(List<ApplicationEvent> applicationEvents) {
        return applicationEvents.stream()
                .map(appEvent -> new ApplicationEventDto(appEvent.getId(), appEvent.getType(), appEvent.getDate(),
                        appEvent.getTime(),appEvent.getInfo()))
                .collect(Collectors.toList());
    }

}
