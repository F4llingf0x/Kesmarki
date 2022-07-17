# Személyeket nyilvántartó, karbantartó console alkalmazás

## Előzmény
Az alkalmazás a Késmárki Szoftverfejlesztő Kft. által hirdetett Java fejlszető pozícióhoz szükséges informatikai
készsegek igazolására irányuló demo projekt.

## Feladatleírás
Készítsen egy személyeket nyilvántartó, karbantartó console alkalmazást. A feladathoz tartozó adatbázis
tartalmazzon 3 táblát. Személyek, címek, elérhetőségek. Egy személynek több címe lehet, egy címhez
több elérhetőség tartozhat.
Az alkalmazás feladatai:
• Adatok lekérdezése
• Adatok rögzítése, módosítása, törlése, hibakezeléssel

## Alkalmazás rövid ismertetése
Az alkalmazás indítása után a konzolon nyílik lehetőség az opciók közötti navigálásra.
Külön menüpontok részletezik mind a személyek, címek és kontaktok CRUD lehetőségeit.
A törlés minden esetben soft delete, azaz biztonságos törlés. Ebből adódóan az applikáción keresztül a törölt
adatok a továbbiakben nem elérhetőek, viszont adatbázis műveletekkel visszaállíthatóak.
