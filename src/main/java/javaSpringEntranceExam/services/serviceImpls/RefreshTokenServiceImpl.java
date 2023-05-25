package javaSpringEntranceExam.services.serviceImpls;

import javaSpringEntranceExam.models.Token;
import javaSpringEntranceExam.repositories.RefreshTokenRepository;
import javaSpringEntranceExam.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Override
    public Token findByToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }


        public Boolean verifyExpiration(String expired) throws ParseException {
            Date currentDay = new Date();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            String dateInString = expired;
            Date expiredDate = formatter.parse(dateInString);

            return currentDay.getTime() > expiredDate.getTime();
        }


}
