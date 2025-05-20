package com.manudev.common.enums;

/**
 * Tipos de transacciones que pueden realizarse en un monedero (wallet).
 */
public enum WalletTransactionTypeDTO {

    /** Retiro de fondos del monedero. */
    WITHDRAWAL,

    /** Transferencia entre monederos. */
    WALLET_TRANSFER,

    /** Adición de dinero al monedero. */
    ADD_MONEY,

    /** Compra de un activo usando el monedero. */
    BUY_ASSET,

    /** Venta de un activo desde el monedero. */
    SELL_ASSET
}
