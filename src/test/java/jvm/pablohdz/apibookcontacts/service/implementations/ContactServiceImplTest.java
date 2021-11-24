package jvm.pablohdz.apibookcontacts.service.implementations;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import jvm.pablohdz.apibookcontacts.mapper.ContactMapper;
import jvm.pablohdz.apibookcontacts.model.Contact;
import jvm.pablohdz.apibookcontacts.model.ContactDto;
import jvm.pablohdz.apibookcontacts.model.ContactRequest;
import jvm.pablohdz.apibookcontacts.model.ContactRequestWitId;
import jvm.pablohdz.apibookcontacts.repository.ContactRepository;
import jvm.pablohdz.apibookcontacts.service.ContactService;
import jvm.pablohdz.apibookcontacts.service.exceptions.ContactIsNotExists;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {
    ContactService contactService;
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private ContactMapper contactMapper;

    @BeforeEach
    void setUp() {
        contactService = new ContactServiceImpl(contactRepository, contactMapper);
    }

    @Test
    void givenContact_whenSaveContact() {
        given(contactRepository.save(any()))
                .willReturn(createFullContact());
        given(contactMapper.contactToContactDto(createFullContact()))
                .willReturn(createFullContactDto());

        ContactDto dto = contactService.create(new ContactRequest(
                "james",
                "8744125672",
                "mobile"
        ));

        assertThat(dto)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void whenReadContacts_thenReturnCollectionContacts() {
        given(contactRepository.findAll())
                .willReturn(List.of(createFullContact(), createFullContact()));
        given(contactMapper.contactToContactDto(createFullContact()))
                .willReturn(createFullContactDto());

        Collection<ContactDto> list = contactService.read();

        assertThat(list.size() > 0)
                .isTrue();
    }

    @Test
    void givenInvalidId_whenDelete_thenThrownException() {
        given(contactRepository.findById(1L))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> contactService.delete(1L))
                .isInstanceOf(ContactIsNotExists.class);
    }

    @Test
    void givenCorrectId_whenDelete() {
        given(contactRepository.findById(1L))
                .willReturn(Optional.of(createFullContact()));

        assertThatCode(() -> contactService.delete(1L))
                .doesNotThrowAnyException();
    }

    @Test
    void givenBadRequest_whenUpdateContact_thenThrowException() {
        given(contactRepository.findById(1L))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> contactService.update(createContactRequestId()))
                .isInstanceOf(ContactIsNotExists.class);
    }

    @NotNull
    private ContactRequestWitId createContactRequestId() {
        return new ContactRequestWitId(1L, "apache",
                "8756903456", "mobile"
        );
    }

    @NotNull
    private ContactDto createFullContactDto() {
        return new ContactDto(
                1L, "james", "8721569078", "mobile",
                LocalDateTime.now().toString(), LocalDateTime.now().toString()
        );
    }

    @NotNull
    private Contact createFullContact() {
        return new Contact(
                "james", "8721569078", "mobile");
    }
}