package com.emard.api.service;

import com.emard.api.domain.FilesInfos;
import com.emard.api.repository.FilesInfosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FilesInfos}.
 */
@Service
@Transactional
public class FilesInfosService {

    private final Logger log = LoggerFactory.getLogger(FilesInfosService.class);

    private final FilesInfosRepository filesInfosRepository;

    public FilesInfosService(FilesInfosRepository filesInfosRepository) {
        this.filesInfosRepository = filesInfosRepository;
    }

    /**
     * Save a filesInfos.
     *
     * @param filesInfos the entity to save.
     * @return the persisted entity.
     */
    public FilesInfos save(FilesInfos filesInfos) {
        log.debug("Request to save FilesInfos : {}", filesInfos);
        return filesInfosRepository.save(filesInfos);
    }

    /**
     * Get all the filesInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FilesInfos> findAll(Pageable pageable) {
        log.debug("Request to get all FilesInfos");
        return filesInfosRepository.findAll(pageable);
    }


    /**
     * Get one filesInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FilesInfos> findOne(Long id) {
        log.debug("Request to get FilesInfos : {}", id);
        return filesInfosRepository.findById(id);
    }

    /**
     * Delete the filesInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FilesInfos : {}", id);
        filesInfosRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<FilesInfos> findAllBis() {
        log.debug("Request to get all FilesInfos");
        return filesInfosRepository.findAllBis();
    }

    @Transactional(readOnly = true)
    public List<FilesInfos> findByCodeFile(String code) {
        log.debug("Request to get by code FilesInfos");
        return filesInfosRepository.findByCodeApplication(code);
    }

    public List<String> getAllFileInFolder(String path){
        List<String> list = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles==null || listOfFiles.length==0) return list;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                list.add(file.getName());
                log.info("file [{}]", file.getName());
            }
        }
        return list;
    }
    /*public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        File folder = new File("C:\\PROJET\\BPR\\OUT");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                list.add(file.getName());
                System.out.println("file [{}]"+ file.getName());
            }
        }
    }*/
}
