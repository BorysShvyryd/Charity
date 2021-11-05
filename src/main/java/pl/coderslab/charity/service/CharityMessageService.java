package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.CharityMessage;
import pl.coderslab.charity.repository.CharityMessageRepository;

import java.util.List;

@Service
public class CharityMessageService {

    private final CharityMessageRepository charityMessageRepository;

    public CharityMessageService(CharityMessageRepository charityMessageRepository) {
        this.charityMessageRepository = charityMessageRepository;
    }

    public List<CharityMessage> findAll() {
        return charityMessageRepository.findAll();
    }

    public CharityMessage getById(Long id) {
        return charityMessageRepository.getById(id);
    }

    public void save(CharityMessage charityMessage) {
        charityMessageRepository.save(charityMessage);
    }

    public void delete(CharityMessage charityMessage) {
        charityMessageRepository.delete(charityMessage);
    }

    public void charityMessageSend(String name, String surname, String message) {
        CharityMessage charityMessage = new CharityMessage();
        charityMessage.setRead(false);
        charityMessage.setName(name);
        charityMessage.setSurname(surname);
        charityMessage.setMessage(message);

        save(charityMessage);
    }
}