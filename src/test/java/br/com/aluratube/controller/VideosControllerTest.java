package br.com.aluratube.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class VideosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void lancaErro404AoBuscarIdNaoExistente() throws Exception {
        URI uri = new URI("/videos/5");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    void lancaErro405AoTentarDeletarCategoriaAssociadaAVideos() throws Exception {
        URI uri = new URI("/categorias/1");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(405));
    }

    @Test
    void getAllFuncionaERetorna200() throws Exception {
        URI uri = new URI("/videos");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void getIdFuncionaERetorna200() throws Exception {
        URI uri = new URI("/videos/1");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void postFuncionaERetorna201() throws Exception {
        URI uri = new URI("/videos");
        String json = "{\"titulo\":\"Aprendendo Java\", \"descricao\":\"Iasmin\", \"url\":\"Url\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void putFuncionaERetorna200() throws Exception {
        URI uri = new URI("/videos/3");
        String json = "{\"titulo\":\"Aprendendo Java\", \"descricao\":\"Iasmin\", \"url\":\"Url\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void deleteFuncionaERetorna200() throws Exception {
        URI uri = new URI("/videos/4");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}