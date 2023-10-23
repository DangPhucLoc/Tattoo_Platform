package SWP391.TattooPlatform.service;

import SWP391.TattooPlatform.model.Artist;
import SWP391.TattooPlatform.model.Feedback;
import SWP391.TattooPlatform.repository.ArtistRepository;
import SWP391.TattooPlatform.repository.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ArtistService {
    final ArtistRepository artistRepository;
    final FeedbackRepository feedbackRepository;


    public ArtistService(ArtistRepository artistRepository, FeedbackRepository feedbackRepository) {
        this.artistRepository = artistRepository;
        this.feedbackRepository = feedbackRepository;
    }


    public List<Artist> getListArtist() {
        if(artistRepository.findAll().isEmpty()) {
            return null;
        }


        return artistRepository.findAll();
    }

    public Artist addArtist(Artist artist)
    {
        if(artistRepository.findArtistByEmail(artist.getEmail()) == null){
            artist.setStatusID("1");
            return artistRepository.save(artist);
        }
        return null;
    }

    public Artist updateArtistInformation(String email
            , String fullName, String phoneNumber, String address ) throws Exception {

        artistRepository.updateArtist(email, fullName, phoneNumber, address);
        return artistRepository.findArtistByEmail(email);
    }

    public Artist updateArtistAccount(String email, String username, String password){
        artistRepository.updateArtistByUsernameAndPassword(email, username, password);
        return artistRepository.findArtistByEmail(email);
    }
    public Artist deleteArtist(String email) throws Exception{
        artistRepository.deleteArtistByEmail(email);
        Artist artist = artistRepository.findArtistByEmail(email);
        if(artist == null) {
            return null;
        }else {
            throw new Exception();
        }
    }

}
