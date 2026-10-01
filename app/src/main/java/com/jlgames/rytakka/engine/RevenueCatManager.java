package com.jlgames.rytakka.engine;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.revenuecat.purchases.Package;
import com.revenuecat.purchases.CustomerInfo;
import com.revenuecat.purchases.Offerings;
import com.revenuecat.purchases.PurchaseParams;
import com.revenuecat.purchases.Purchases;
import com.revenuecat.purchases.PurchasesConfiguration;
import com.revenuecat.purchases.EntitlementInfo;
import com.revenuecat.purchases.PurchasesError;
import com.revenuecat.purchases.interfaces.ReceiveCustomerInfoCallback;
import com.revenuecat.purchases.interfaces.ReceiveOfferingsCallback;
import com.revenuecat.purchases.interfaces.PurchaseCallback;
import com.revenuecat.purchases.models.StoreTransaction;

/** Luokka RevenueCatin APIn toimivuutta varten
 * Testattu yhdellä premium-skinillä.
 * Myös lokit mukana myöhempää tarkastelua varten mukaan lukien virhelokien override.
*/
public class RevenueCatManager {

    private static final String tag = "RevenueCat";
    private static final String TUOTE = "lepakkomies_skini";
    private static final String ENTITLEMENT = "lepakkomies_skini";
    private final Context context;
    private final Activity activity;
    private Package skinit;
    private boolean skinitAvattu = false;

    public RevenueCatManager(Context context, Activity activity){
        this.context = context.getApplicationContext();
        this.activity = activity;
    }

    // Käytetään kovakoodattua testi-API-avainta.
    public void konfiguroi() {
        Purchases.configure(
            new PurchasesConfiguration.Builder(context, "test_EwXxPpjpvvmomNgSdIVZsTuGoMW").build()
        );
        Log.d(tag, "RevenueCat initialized");
    }

    public void lataaTuotteet() {
        Purchases.getSharedInstance().getOfferings(
            new ReceiveOfferingsCallback() {
                @Override
                public void onReceived(Offerings offerings) {
                    if (offerings.getCurrent() == null) {
                        Log.d(tag, "Offering puuttuu.");
                        return;
                    }
                    for (Package packageItem : offerings.getCurrent().getAvailablePackages()) {
                        String productId = packageItem.getProduct().getId();
                        Log.d(tag, "Product: " + productId);

                        if (TUOTE.equals(productId)) {
                            skinit = packageItem;
                            Log.d(tag, "I'm Batman!");
                        }
                    }
                }

                @Override
                public void onError(PurchasesError error) {
                    Log.e(tag, "Tuotelatausvirhe: " + error.getMessage());
                }
            }
        );
    }

    // Tällä hetkellä vain lepakkomies-skini
    public void ostaSkinit() {

        if (skinit == null) {
            Log.e(tag, "Skinit -tuotetta ei ole ladattu");
            return;
        }
        PurchaseParams purchaseParams = new PurchaseParams.Builder(this.activity, skinit).build();
        Purchases.getSharedInstance().purchase(
            purchaseParams,
            new PurchaseCallback() {

                @Override
                public void onCompleted(StoreTransaction storeTransaction, CustomerInfo customerInfo) {

                    if (omistaaSkinit(customerInfo)) {

                        skinitAvattu = true;

                        Log.d(tag, "Skinit ostettu!");
                    }
                }

                @Override
                public void onError(PurchasesError error, boolean userCancelled) {

                    if (userCancelled) {

                        Log.d(tag, "Käyttäjä peruutti oston");

                    } else {
                        Log.e(tag, "Osto epäonnistui: " + error);
                    }
                }
            }
        );
    }
    private boolean omistaaSkinit(CustomerInfo customerInfo) {

        EntitlementInfo entitlement = customerInfo.getEntitlements().get(ENTITLEMENT);
        return entitlement != null && entitlement.isActive();
    }
    public void tarkistaSkinit() {

        Purchases.getSharedInstance().getCustomerInfo(
            new ReceiveCustomerInfoCallback() {

                @Override
                public void onReceived(CustomerInfo customerInfo) {

                    skinitAvattu = omistaaSkinit(customerInfo);

                    Log.d(tag, "Skini avattu: " + skinitAvattu);
                }

                @Override
                public void onError(PurchasesError error) {

                    Log.e(tag, "CustomerInfo error: " + error.getMessage());
                }
            }
        );
    }
    public boolean onkoSkinitAvattu() {
        return skinitAvattu;
    }

}
