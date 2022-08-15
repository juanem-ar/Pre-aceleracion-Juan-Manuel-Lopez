package com.alkemy.disney.service;

import com.alkemy.disney.dto.GenderDTO;

import java.util.List;

public interface GenderService {
    GenderDTO save(GenderDTO dto);
    List<GenderDTO> getAllGenders();
    GenderDTO update(Long id, GenderDTO gender);
    void delete (Long id);
}
