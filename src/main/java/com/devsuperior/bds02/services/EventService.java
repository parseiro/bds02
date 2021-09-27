package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;


    @Transactional
    public Event update(Long id, EventDTO dto) {
        try {
            var entity = eventRepository.getOne(id);
            copyDtoToEntity(dto, entity);
            return eventRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    private void copyDtoToEntity(EventDTO dto, Event entity) {

        entity.setName(dto.getName());
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.setCity(cityRepository.getOne(dto.getCityId()));

    }
}
