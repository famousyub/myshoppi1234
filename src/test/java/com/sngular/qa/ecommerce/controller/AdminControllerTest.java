package com.sngular.qa.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sngular.qa.ecommerce.dto.GraphQLRequest;
import com.sngular.qa.ecommerce.dto.perfume.PerfumeRequest;
import com.sngular.qa.ecommerce.dto.user.UserRequest;
import com.sngular.qa.ecommerce.util.TestConstants;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithUserDetails(TestConstants.ADMIN_EMAIL)
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-user-before.sql", "/sql/create-perfumes-before.sql", "/sql/create-orders-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/create-orders-after.sql", "/sql/create-perfumes-after.sql", "/sql/create-user-after.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private GraphQLRequest graphQLRequest;
    private PerfumeRequest perfumeRequest;

    @Before
    public void init() {
        graphQLRequest = new GraphQLRequest();
        perfumeRequest = new PerfumeRequest();
        perfumeRequest.setPerfumer(TestConstants.PERFUMER_CHANEL);
        perfumeRequest.setPerfumeTitle(TestConstants.PERFUME_TITLE);
        perfumeRequest.setYear(TestConstants.YEAR);
        perfumeRequest.setCountry(TestConstants.COUNTRY);
        perfumeRequest.setPerfumeGender(TestConstants.PERFUME_GENDER);
        perfumeRequest.setFragranceTopNotes(TestConstants.FRAGRANCE_TOP_NOTES);
        perfumeRequest.setFragranceMiddleNotes(TestConstants.FRAGRANCE_MIDDLE_NOTES);
        perfumeRequest.setFragranceBaseNotes(TestConstants.FRAGRANCE_BASE_NOTES);
        perfumeRequest.setPrice(TestConstants.PRICE);
        perfumeRequest.setVolume(TestConstants.VOLUME);
        perfumeRequest.setType(TestConstants.TYPE);
    }

    @Test
    public void addPerfume() throws Exception {
        FileInputStream inputFile = new FileInputStream(new File(TestConstants.FILE_PATH));
        MockMultipartFile multipartFile = new MockMultipartFile("file", TestConstants.FILE_NAME, MediaType.MULTIPART_FORM_DATA_VALUE, inputFile);
        MockMultipartFile jsonFile = new MockMultipartFile("perfume", "", MediaType.APPLICATION_JSON_VALUE, mapper.writeValueAsString(perfumeRequest).getBytes());
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(MockMvcRequestBuilders.multipart(TestConstants.URL_ADMIN_ADD)
                .file(multipartFile)
                .file(jsonFile))
                .andExpect(status().isOk());
    }

    @Test
    public void addPerfume_ShouldInputFieldsAreEmpty() throws Exception {
        PerfumeRequest perfumeRequest = new PerfumeRequest();
        MockMultipartFile jsonFile = new MockMultipartFile("perfume", "", MediaType.APPLICATION_JSON_VALUE, mapper.writeValueAsString(perfumeRequest).getBytes());
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(MockMvcRequestBuilders.multipart(TestConstants.URL_ADMIN_ADD)
                .file(jsonFile))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.perfumeTitleError", is("Fill in the input field")))
                .andExpect(jsonPath("$.perfumerError", is("Fill in the input field")))
                .andExpect(jsonPath("$.yearError", is("Fill in the input field")))
                .andExpect(jsonPath("$.countryError", is("Fill in the input field")))
                .andExpect(jsonPath("$.perfumeGenderError", is("Fill in the input field")))
                .andExpect(jsonPath("$.fragranceTopNotesError", is("Fill in the input field")))
                .andExpect(jsonPath("$.fragranceMiddleNotesError", is("Fill in the input field")))
                .andExpect(jsonPath("$.fragranceBaseNotesError", is("Fill in the input field")))
                .andExpect(jsonPath("$.priceError", is("Fill in the input field")))
                .andExpect(jsonPath("$.volumeError", is("Fill in the input field")))
                .andExpect(jsonPath("$.typeError", is("Fill in the input field")));
    }

    @Test
    public void editPerfume() throws Exception {
        FileInputStream inputFile = new FileInputStream(new File(TestConstants.FILE_PATH));
        MockMultipartFile multipartFile = new MockMultipartFile("file", TestConstants.FILE_NAME, MediaType.MULTIPART_FORM_DATA_VALUE, inputFile);
        MockMultipartFile jsonFileEdit = new MockMultipartFile("perfume", "", MediaType.APPLICATION_JSON_VALUE, mapper.writeValueAsString(perfumeRequest).getBytes());
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        perfumeRequest.setType("test");
        mockMvc.perform(MockMvcRequestBuilders.multipart(TestConstants.URL_ADMIN_EDIT)
                .file(multipartFile)
                .file(jsonFileEdit))
                .andExpect(status().isOk());
    }

    @Test
    public void editPerfume_ShouldInputFieldsAreEmpty() throws Exception {
        PerfumeRequest perfumeRequest = new PerfumeRequest();
        MockMultipartFile jsonFile = new MockMultipartFile("perfume", "", MediaType.APPLICATION_JSON_VALUE, mapper.writeValueAsString(perfumeRequest).getBytes());
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(MockMvcRequestBuilders.multipart(TestConstants.URL_ADMIN_EDIT)
                .file(jsonFile))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.perfumeTitleError", is("Fill in the input field")))
                .andExpect(jsonPath("$.perfumerError", is("Fill in the input field")))
                .andExpect(jsonPath("$.yearError", is("Fill in the input field")))
                .andExpect(jsonPath("$.countryError", is("Fill in the input field")))
                .andExpect(jsonPath("$.perfumeGenderError", is("Fill in the input field")))
                .andExpect(jsonPath("$.fragranceTopNotesError", is("Fill in the input field")))
                .andExpect(jsonPath("$.fragranceMiddleNotesError", is("Fill in the input field")))
                .andExpect(jsonPath("$.fragranceBaseNotesError", is("Fill in the input field")))
                .andExpect(jsonPath("$.priceError", is("Fill in the input field")))
                .andExpect(jsonPath("$.volumeError", is("Fill in the input field")))
                .andExpect(jsonPath("$.typeError", is("Fill in the input field")));
    }

    @Test
    public void deletePerfume() throws Exception {
        mockMvc.perform(delete(TestConstants.URL_ADMIN_BASIC + "/delete/46"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].perfumeTitle").isNotEmpty())
                .andExpect(jsonPath("$[*].perfumer").isNotEmpty())
                .andExpect(jsonPath("$[*].year").isNotEmpty())
                .andExpect(jsonPath("$[*].country").isNotEmpty())
                .andExpect(jsonPath("$[*].perfumeGender").isNotEmpty())
                .andExpect(jsonPath("$[*].fragranceTopNotes").isNotEmpty())
                .andExpect(jsonPath("$[*].fragranceMiddleNotes").isNotEmpty())
                .andExpect(jsonPath("$[*].fragranceBaseNotes").isNotEmpty())
                .andExpect(jsonPath("$[*].description").isNotEmpty())
                .andExpect(jsonPath("$[*].filename").isNotEmpty())
                .andExpect(jsonPath("$[*].price").isNotEmpty())
                .andExpect(jsonPath("$[*].volume").isNotEmpty())
                .andExpect(jsonPath("$[*].type").isNotEmpty())
                .andExpect(jsonPath("$[*].reviews[*]", iterableWithSize(greaterThan(1))))
                .andExpect(jsonPath("$[*].reviews[*].author").isNotEmpty());
    }

    @Test
    public void getAllOrders() throws Exception {
        mockMvc.perform(get(TestConstants.URL_ADMIN_BASIC + "/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].totalPrice", Matchers.hasItem(TestConstants.TOTAL_PRICE)))
                .andExpect(jsonPath("$[*].date").isNotEmpty())
                .andExpect(jsonPath("$[*].firstName", Matchers.hasItem(TestConstants.FIRST_NAME)))
                .andExpect(jsonPath("$[*].lastName", Matchers.hasItem(TestConstants.LAST_NAME)))
                .andExpect(jsonPath("$[*].city", Matchers.hasItem(TestConstants.CITY)))
                .andExpect(jsonPath("$[*].address", Matchers.hasItem(TestConstants.ADDRESS)))
                .andExpect(jsonPath("$[*].email", Matchers.hasItem(TestConstants.USER_EMAIL)))
                .andExpect(jsonPath("$[*].phoneNumber", Matchers.hasItem(TestConstants.PHONE_NUMBER)))
                .andExpect(jsonPath("$[*].postIndex", Matchers.hasItem(TestConstants.POST_INDEX)))
                .andExpect(jsonPath("$[*].orderItems").isNotEmpty());
    }

    @Test
    public void getUserOrdersByEmail() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(TestConstants.USER_EMAIL);

        mockMvc.perform(post(TestConstants.URL_ADMIN_BASIC + "/order")
                .content(mapper.writeValueAsString(userRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].totalPrice", Matchers.hasItem(TestConstants.TOTAL_PRICE)))
                .andExpect(jsonPath("$[*].date").isNotEmpty())
                .andExpect(jsonPath("$[*].firstName", Matchers.hasItem(TestConstants.FIRST_NAME)))
                .andExpect(jsonPath("$[*].lastName", Matchers.hasItem(TestConstants.LAST_NAME)))
                .andExpect(jsonPath("$[*].city", Matchers.hasItem(TestConstants.CITY)))
                .andExpect(jsonPath("$[*].address", Matchers.hasItem(TestConstants.ADDRESS)))
                .andExpect(jsonPath("$[*].email", Matchers.hasItem(TestConstants.USER_EMAIL)))
                .andExpect(jsonPath("$[*].phoneNumber", Matchers.hasItem(TestConstants.PHONE_NUMBER)))
                .andExpect(jsonPath("$[*].postIndex", Matchers.hasItem(TestConstants.POST_INDEX)))
                .andExpect(jsonPath("$[*].orderItems").isNotEmpty());
    }

    @Test
    public void deleteOrder() throws Exception {
        mockMvc.perform(delete(TestConstants.URL_ADMIN_BASIC + "/order/delete/111"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(TestConstants.URL_ADMIN_GET_USER + TestConstants.USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TestConstants.USER_ID))
                .andExpect(jsonPath("$.firstName").value(TestConstants.FIRST_NAME))
                .andExpect(jsonPath("$.email").value(TestConstants.USER_EMAIL));
    }

    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(get(TestConstants.URL_ADMIN_GET_USER + "all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", Matchers.hasItem(TestConstants.USER_ID)))
                .andExpect(jsonPath("$[*].firstName", Matchers.hasItem(TestConstants.FIRST_NAME)))
                .andExpect(jsonPath("$[*].email", Matchers.hasItem(TestConstants.USER_EMAIL)));
    }

    @Test
    public void getUserByQuery() throws Exception {
        graphQLRequest.setQuery(TestConstants.GRAPHQL_QUERY_USER);

        mockMvc.perform(post(TestConstants.URL_ADMIN_GRAPHQL + "/user")
                .content(mapper.writeValueAsString(graphQLRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user.id", equalTo(TestConstants.USER_ID)))
                .andExpect(jsonPath("$.data.user.email", equalTo(TestConstants.USER_EMAIL)))
                .andExpect(jsonPath("$.data.user.firstName", equalTo(TestConstants.FIRST_NAME)))
                .andExpect(jsonPath("$.data.user.lastName", equalTo(TestConstants.LAST_NAME)))
                .andExpect(jsonPath("$.data.user.city", equalTo(TestConstants.CITY)))
                .andExpect(jsonPath("$.data.user.address", equalTo(TestConstants.ADDRESS)))
                .andExpect(jsonPath("$.data.user.phoneNumber", equalTo(TestConstants.PHONE_NUMBER)))
                .andExpect(jsonPath("$.data.user.postIndex", equalTo("1234567890")))
                .andExpect(jsonPath("$.data.user.activationCode", equalTo(null)))
                .andExpect(jsonPath("$.data.user.passwordResetCode", equalTo(null)))
                .andExpect(jsonPath("$.data.user.active", equalTo(true)))
                .andExpect(jsonPath("$.data.user.roles[0]", equalTo(TestConstants.ROLE_USER)));
    }

    @Test
    public void getUsersByQuery() throws Exception {
        graphQLRequest.setQuery(TestConstants.GRAPHQL_QUERY_USERS);

        mockMvc.perform(post(TestConstants.URL_ADMIN_GRAPHQL + "/user/all")
                .content(mapper.writeValueAsString(graphQLRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.users[*].id").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].email").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].firstName").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].lastName").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].city").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].address").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].phoneNumber").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].postIndex").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].activationCode").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].passwordResetCode").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].active").isNotEmpty())
                .andExpect(jsonPath("$.data.users[*].roles").isNotEmpty());
    }

    @Test
    public void getOrdersByQuery() throws Exception {
        graphQLRequest.setQuery(TestConstants.GRAPHQL_QUERY_ORDERS);

        mockMvc.perform(post(TestConstants.URL_ADMIN_GRAPHQL + "/orders")
                .content(mapper.writeValueAsString(graphQLRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.orders[*].id").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].totalPrice").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].date").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].firstName").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].lastName").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].city").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].address").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].email").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].phoneNumber").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].postIndex").isNotEmpty())
                .andExpect(jsonPath("$.data.orders[*].orderItems[*].perfume").isNotEmpty());
    }

    @Test
    public void getUserOrdersByEmailQuery() throws Exception {
        graphQLRequest.setQuery(TestConstants.GRAPHQL_QUERY_ORDERS_BY_EMAIL);

        mockMvc.perform(post(TestConstants.URL_ADMIN_GRAPHQL + "/order")
                .content(mapper.writeValueAsString(graphQLRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.ordersByEmail[*].id").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].totalPrice").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].date").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].firstName").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].lastName").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].city").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].address").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].email").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].phoneNumber").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].postIndex").isNotEmpty())
                .andExpect(jsonPath("$.data.ordersByEmail[*].orderItems[*].perfume").isNotEmpty());
    }
}
