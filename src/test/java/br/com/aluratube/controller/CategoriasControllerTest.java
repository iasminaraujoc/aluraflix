package br.com.aluratube.controller;

import br.com.aluratube.controller.exception.ItemNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void devolve400ComCorNulaPost() throws Exception {
        URI uri = new URI("/categorias");
        String json = "{\"titulo\":\"Front-end\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void devolve400ComTituloNuloPost() throws Exception {
        URI uri = new URI("/categorias");
        String json = "{\"cor\":\"Rosa\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void lancaErro404AoBuscarIdNaoExistente() throws Exception {
        URI uri = new URI("/categorias/5");

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
        URI uri = new URI("/categorias");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                        .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void getIdFuncionaERetorna200() throws Exception {
        URI uri = new URI("/categorias/1");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void getVideoPorCategoriaFuncionaERetorna200() throws Exception {
        URI uri = new URI("/categorias/1/videos");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void postFuncionaERetorna201() throws Exception {
        URI uri = new URI("/categorias");
        String json = "{\"titulo\":\"Front-end\", \"cor\":\"Rosa\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void putFuncionaERetorna200() throws Exception {
        URI uri = new URI("/categorias/3");
        String json = "{\"titulo\":\"Front-end\", \"cor\":\"Rosa\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void deleteFuncionaERetorna200() throws Exception {
        URI uri = new URI("/categorias/4");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}