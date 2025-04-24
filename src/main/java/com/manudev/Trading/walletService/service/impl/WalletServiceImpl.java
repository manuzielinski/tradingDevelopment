package com.manudev.Trading.walletService.service.impl;

import com.manudev.Trading.userService.model.UserEntity;
import com.manudev.Trading.OrderService.domain.OrderType;
import com.manudev.Trading.OrderService.model.Order;
import com.manudev.Trading.walletService.model.Wallet;
import com.manudev.Trading.walletService.repository.WalletRepository;
import com.manudev.Trading.walletService.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {


    @Autowired
    private WalletRepository walletRepository;
    @Override
    public Wallet getUserWallet(UserEntity user) {
        Wallet wallet = walletRepository.findWalletByUserId(user.getUserID());

        if(wallet==null) {
            wallet = new Wallet();
            wallet.setUser(user);
        }
        return wallet;
    }

    @Override
    public Wallet addBalance(Wallet wallet, Long amount) {
        // primero buscamos el balance
        BigDecimal balance = wallet.getBalance();
        // luego agregamos el nuevo balance
        BigDecimal newBalance = balance.add(BigDecimal.valueOf(amount));
        // finalmente lo seteamos
        wallet.setBalance(newBalance);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletById(Long walletId) throws Exception {
        Optional<Wallet> wallet = walletRepository.findById(walletId);

        if(wallet.isPresent()) {
            return wallet.get();
        } else {
            throw new Exception("Wallet not found");
        }
    }

    @Override
    public Wallet WalletToWalletTransfer(UserEntity sender, Wallet receiver, Long amount) throws Exception {
        Wallet senderWallet = getUserWallet(sender);

        if (senderWallet.getBalance().compareTo(BigDecimal.valueOf(amount))<0){
            throw new Exception("Insufficient Balance");
        }

        BigDecimal senderBalance = senderWallet.getBalance().subtract(BigDecimal.valueOf(amount));
        senderWallet.setBalance(senderBalance);
        walletRepository.save(senderWallet);

        BigDecimal receiverBalance = receiver.getBalance().add(BigDecimal.valueOf(amount));
        receiver.setBalance(receiverBalance);
        walletRepository.save(receiver);
        return senderWallet;
    }

    @Override
    public Wallet payOrderPayment(Order order, UserEntity user) throws Exception {
        Wallet wallet = getUserWallet(user);

        if(order.getOrderType().equals(OrderType.BUY)){
            BigDecimal newBalance = wallet.getBalance().subtract(order.getPrice());
            if(newBalance.compareTo(order.getPrice())<0){
                throw new Exception("Insufficient funds for this transaction");
            }
            wallet.setBalance(newBalance);
        } else {
            BigDecimal newBalance = wallet.getBalance().add(order.getPrice());
            wallet.setBalance(newBalance);
        }
        walletRepository.save(wallet);
        return wallet;
    }
}
