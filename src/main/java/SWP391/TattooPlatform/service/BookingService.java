package SWP391.TattooPlatform.service;

import SWP391.TattooPlatform.config.ResponseUtils;
import SWP391.TattooPlatform.model.BookingDetail;
import SWP391.TattooPlatform.model.BookingStatus;
import SWP391.TattooPlatform.model.Voucher;
import SWP391.TattooPlatform.repository.BookingDetailRepository;
import SWP391.TattooPlatform.repository.BookingRepository;
import SWP391.TattooPlatform.model.Booking;
import SWP391.TattooPlatform.repository.BookingStatusRepository;
import SWP391.TattooPlatform.repository.VoucherRepository;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

    final BookingRepository bookingRepository;
    final BookingDetailRepository bookingDetailRepository;

    final BookingStatusRepository bookingStatusRepository;

    final VoucherRepository voucherRepository;

    public BookingService(BookingRepository bookingRepository, BookingDetailRepository bookingDetailRepository,
                           BookingStatusRepository bookingStatusRepository, VoucherRepository voucherRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingDetailRepository = bookingDetailRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.voucherRepository = voucherRepository;
    }

    public Booking getBookingByID(String bookingID) {
        if(bookingRepository.findBookingByBookingID(bookingID) == null) {
            return null;
        }
        return bookingRepository.findBookingByBookingID(bookingID);
    }

    public List<Booking> findall() {
        return bookingRepository.findAll();
    }





    public Booking addBooking(Booking b) {
            b.setTotalPrice((float) 0);
             bookingRepository.save(b);
            return b;
        }
    public void addBookingDetail( List<BookingDetail> bookingDetails , String id) {
        float totalPrice = 0;
        BookingStatus status = bookingStatusRepository.findBookingStatusByStatusName("Confirmed");
        for(BookingDetail bookingDetail : bookingDetails) {

            bookingDetail.setBookingID(id);
            Booking booking = bookingRepository.findBookingByBookingID(id);
            bookingDetail.setStatusID(status.getStatusID());

            if(bookingDetail.getVoucherID()!=null) {
                Voucher voucher = voucherRepository.findVoucherByVoucherID(bookingDetail.getVoucherID());
                float actualPrice = (bookingDetail.getPrice()*(100 - voucher.getDiscount()))/100;
                bookingDetail.setPrice(actualPrice);
            }
            bookingDetailRepository.save(bookingDetail);
            totalPrice += bookingDetail.getPrice();

        }
        Booking booking = bookingRepository.findBookingByBookingID(id);
        bookingRepository.updatePrice(totalPrice,id);
        bookingRepository.deleteWhenPrice0();


    }

    public Booking updateBooking(String bookingID,
                                 String tattooLoverEmail,
                                 String customerName, String customerPhoneNumber, float totalPrice) {
        bookingRepository.updateBooking(bookingID,tattooLoverEmail.trim(),customerName,customerPhoneNumber,totalPrice);
        return bookingRepository.findBookingByBookingID(bookingID);
    }

    public Booking deleteBooking(String bookingID) throws Exception {
        bookingRepository.delete(bookingID);
        if( bookingRepository.findBookingByBookingID(bookingID) == null) {
            return null;
        }else {
            throw new Exception();
        }
    }



}
