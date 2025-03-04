**AutomationExercise - Test Case**
Açıklama: Bu proje, AutomationExercise.com adlı bir e-ticaret sitesinin ürün sayfasındaki ürünleri otomatik olarak test eden bir Selenium WebDriver test senaryosudur. Test, ürünlerin resimlerinin geçerliliğini kontrol eder, ürün bilgilerini alır ve bu bilgileri JSON formatında kaydeder.

**Başlangıç**
Bu proje, Selenium WebDriver ile otomatikleştirilmiş testlerin yapılması amacıyla yazılmıştır. Test, AutomationExercise.com sitesindeki ürün sayfasındaki her bir ürünün resimlerini kontrol eder, geçerli resimler ile birlikte ürün bilgilerini JSON formatında kaydeder.

**Proje Hedefi**
Projenin amacı, AutomationExercise.com sitesindeki ürün sayfasının işlevselliğini test etmek ve ürün bilgilerini dışa aktarmaktır. Test, her bir ürün için:

- Ürün adı
- Ürün fiyatı
- Ürün kategorisi
- Ürün alt kategorisi
- Ürün bağlantısı
- Ürün resmi (Resim geçerli ise base64 formatında kaydedilir)

Bu bilgileri JSON dosyalarına yazarak iki kategoride toplar:

1. read.json: Geçerli resme sahip ürünler
2. unread.json: Geçerli resme sahip olmayan ürünler

**Gereksinimler**
Projenin çalışabilmesi için aşağıdaki yazılımlar ve kütüphaneler gereklidir:

- Java 8+
- Maven (Projeyi derlemek ve bağımlılıkları yönetmek için)
- Selenium WebDriver (Web tarayıcılarını otomatikleştirmek için)
- WebDriverManager (Tarayıcı sürücülerini otomatik olarak yönetmek için)
- SLF4J (Loglama için)
- Gson (JSON verilerini işlemek için)

Bağımlılıkları yüklemek için, Maven kullanarak aşağıdaki komutları çalıştırabilirsiniz:

> `mvn clean install
`
**Kurulum**
Projeyi GitHub'dan klonlayarak başlatabilirsiniz.

**Projeyi Klonlayın:**
`git clone https://github.com/Turgaysarigedik7/Get-Data-Product-Test-Case.git
`
**Bağımlılıkları İndirin:**
`cd Get-Data-Product-Test-Case
mvn clean install
`
WebDriver için ChromeDriver'ı Ayarlayın: Proje WebDriverManager kullanarak ChromeDriver'ı otomatik olarak ayarlayacaktır, ancak doğru sürücünün kurulu olduğundan emin olmalısınız.

**Kullanım**
Testi çalıştırmak için, JUnit ile çalışan bir Java test dosyasına sahiptir. Aşağıdaki adımları izleyerek testi çalıştırabilirsiniz:

**Kodun Genel İşleyişi:**
Test Setup (@Before):

**Chrome Driver Başlatılıyor:**
ChromeOptions kullanılarak başsız (headless) Chrome tarayıcısı başlatılıyor. Yani tarayıcı, kullanıcı arayüzü olmadan çalışıyor.
WebDriver Başlatılıyor: WebDriver, ChromeDriver olarak başlatılıyor.

Test Case (@Test):

Web Sitesine Gitmek: driver.get("http://automationexercise.com"); komutu ile belirtilen URL'ye gidiliyor.

Ürün Sayfası Kontrolü: Web sayfasındaki "Features Items" başlığının görünür olup olmadığı Assert.assertTrue() ile kontrol ediliyor.

Ürünlere Tıklama: "Products" linkine tıklanıyor ve ürünler listesindeki her bir ürün için işlem yapılıyor.

Ürün Resmi Kontrolü: Ürünün detay sayfasına gidiliyor ve ürünün resminin geçerli olup olmadığı kontrol ediliyor. Eğer resim geçerli değilse, "unread.json" dosyasına kaydediliyor. 

Geçerli bir resim varsa, ürün detaylarıyla birlikte "read.json" dosyasına kaydediliyor.

Verilerin JSON Olarak Kaydedilmesi: Ürün bilgileri JSON formatında dosyaya kaydediliyor. 

Bu bilgiler arasında ürün adı, fiyatı, linki, kategorisi ve alt kategorisi yer alıyor. Ayrıca resmin URL'si de ekleniyor.

**After Test (@After):**

Tarayıcı Kapatma:
 
Test tamamlandığında tarayıcı kapatılıyor.

**Kullanılan Ana Bileşenler:**

Selenium WebDriver: Web sayfasını otomatik olarak kontrol etmek ve etkileşimde bulunmak için kullanılıyor.

JSON İşlemleri: Ürün bilgileri JSON formatında kaydediliyor. Gson kütüphanesi kullanılarak JSON dosyaları oluşturuluyor ve veriler yazılıyor.

Resim Kontrolü: Resimlerin geçerliliği kontrol edilerek, geçerli olmayan resimler bir JSON dosyasına kaydediliyor. Bunun için ImageIO.read() metodu kullanılıyor.

WebDriverManager: ChromeDriver'ı otomatik olarak yöneten bir kütüphane.

**Önemli Fonksiyonlar:**

isImageControl(String url): Verilen URL'deki resmin geçerli olup olmadığını kontrol eder. Eğer resim indirilemiyorsa, false döner.

writeJSONToFile(String filename, JsonArray data): JSON verisini belirtilen dosyaya kaydeder.

readJSONFromFile(String filename): JSON dosyasından veriyi okur.

**Kullanılan Kütüphaneler:**
Selenium WebDriver: Web otomasyonunu sağlar.

Gson: JSON verilerini oluşturmak ve işlemek için kullanılır.

SLF4J: Logger kullanımı için kullanılır, bu da hataları ve log mesajlarını kaydeder.


**Testi başlatın:**

IntelliJ IDEA veya Eclipse gibi bir IDE kullanıyorsanız, TestCase.java dosyasını çalıştırabilirsiniz.

Alternatif olarak, Maven komutu ile test çalıştırabilirsiniz:
`mvn test
`
Test tamamlandığında, iki JSON dosyası oluşturulacaktır:

1. read.json: Geçerli resimlere sahip ürün bilgileri.
2. unread.json: Geçerli resimlere sahip olmayan ürün bilgileri.
3. 
**Testler**
Bu proje için yalnızca bir test senaryosu bulunmaktadır:

**ReadProductTestCase:**
AutomationExercise.com sitesine gider.

Ürünler sayfasını ziyaret eder.

Her bir ürünü kontrol eder.

Resmin geçerliliğini kontrol eder (eğer geçerli değilse unread.json dosyasına kaydeder, geçerliyse read.json dosyasına kaydeder).

Ürün bilgilerini JSON formatında kaydeder.

Kod Yapısı
**Projenin genel yapısı şu şekildedir:**

`/Get-Data-Product-Test-Case
    ├── /src
        ├── /main
            ├── /java
                ├── /com
                    ├── /AutomationExercise
                        ├── TestCase.java          # Test senaryoları
                        ├── ImageData.java         # Resim verisi işlemleri
                        ├── JSONHandler.java       # JSON dosya okuma ve yazma işlemleri
`
TestCase.java: Ürünlerin kontrol edildiği ana test dosyasıdır.

ImageData.java: Resimlerin base64 formatına dönüştürülmesini sağlar.

JSONHandler.java: JSON verilerinin dosyaya yazılmasını ve okunmasını sağlar.

**Yazarlar**
Turgay Sarıgedik - Proje sahibi ve baş geliştirici
