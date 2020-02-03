# CarWorkshop

Link do frontu:
https://github.com/kwisniowski/CarWorkshop_front

Aplikacja zapisuje klientów, samochody, naprawy, części zamienne.

APlikacja frontendowa uruchamiana jest na porcie 8090. Większość funkcji jest widoczna od razu, niektóre pojawiają się dopierpo po wciśnięciu
przycisków. Żeby prawidłowo przejść uzupełnienie wszystkich danych proponuję w takiej kolejności: 
1. Dodajemy nowego użytkownika 
2. Dodajemy samochód za pomocą przycisku Add car to customer
3. Dodajemy naprawę do samochodu przyciskiem Add repair.
4. Wprowadzamy do pamięci częśc zamienną. 
5. Dodajemy koszt do naprawy - Add cost to repair - wskazujemy na liście częsci to, którą chcemy dodać, dodajemy ilośc zużytych części. Po 
potwierdzeniu koszt zostaje automatycznie naliczony. 

API zewn1 - sprawdzanie statusu klienta w bazie Ministerstwa Finansów - jako okno pop-up pokazywane są najważniejsze informacje o kliencie,
można to łatow rozbudować o edycję danych klienta w bazie na podstawi tych pobranych z MF.
API zewn2 - pobieranie kursów walut z NBP. W sekcji napraw mamy do dyspozycji kalkulator - pozwala obliczyć należność, gdyby klient 
chciał zapłacić w innej walucie.

Jako potencjał do rozwoju aplikacji widzę dołożenie modułu faktuer na który brakło mi niestety czasu, interakcji z klientenm np zamawianie
samochodu zastępczego nad czym pracowałem ale nie ukończyłem. 



