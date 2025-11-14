import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MataKuliah {
    private String kode;
    private String nama;
    private int sks;

    public MataKuliah(String kode, String nama, int sks) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public int getSks() { return sks; }

    @Override
    public String toString() {
        return String.format("%-10s | %-30s | %2d SKS", kode, nama, sks);
    }
}

class Mahasiswa {
    private String nim;
    private String nama;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
}

class KRS {
    private Mahasiswa mhs;
    private List<MataKuliah> daftarMK;
    public static final int MAX_SKS = 24;

    public KRS(Mahasiswa mhs) {
        this.mhs = mhs;
        this.daftarMK = new ArrayList<>();
    }

    public int totalSks() {
        int sum = 0;
        for (MataKuliah mk : daftarMK) sum += mk.getSks();
        return sum;
    }

    public boolean tambahMK(MataKuliah mk) {
        if (totalSks() + mk.getSks() > MAX_SKS) return false;
        daftarMK.add(mk);
        return true;
    }

    public List<MataKuliah> getDaftarMK() { return daftarMK; }

    public void cetakKRS() {
        System.out.println("===============================================");
        System.out.println("                KARTU RENCANA STUDI            ");
        System.out.println("===============================================");
        System.out.printf("NIM  : %s\n", mhs.getNim());
        System.out.printf("Nama : %s\n", mhs.getNama());
        System.out.println("-----------------------------------------------");
        System.out.printf("%-10s | %-30s | %-6s\n", "Kode", "Nama Mata Kuliah", "SKS");
        System.out.println("-----------------------------------------------");
        for (MataKuliah mk : daftarMK) {
            System.out.println(mk.toString());
        }
        System.out.println("-----------------------------------------------");
        System.out.printf("Total SKS diambil: %d\n", totalSks());
        System.out.println("-----------------------------------------------");
        // Tanda tangan KPS sesuai nim dan nama (diminta)
        System.out.println("\nTTD KPS,");
        System.out.println();
        System.out.printf("%s\nNIM: %s\n", mhs.getNama(), mhs.getNim());
        System.out.println("===============================================");
    }
}

public class krsOOP {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== APLIKASI OOP PENGISIAN KRS ===");
        System.out.print("Masukkan NIM: ");
        String nim = sc.nextLine().trim();
        System.out.print("Masukkan Nama Mahasiswa: ");
        String nama = sc.nextLine().trim();

        Mahasiswa mhs = new Mahasiswa(nim, nama);
        KRS krs = new KRS(mhs);

        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Mata Kuliah");
            System.out.println("2. Cetak KRS");
            System.out.println("3. Selesai (Exit)");
            System.out.print("Pilih (1-3): ");
            String pilihan = sc.nextLine().trim();
            switch (pilihan) {
                case "1":
                    tambahMKFlow(krs);
                    break;
                case "2":
                    krs.cetakKRS();
                    break;
                case "3":
                    System.out.println("Keluar. Terima kasih.");
                    lanjut = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void tambahMKFlow(KRS krs) {
        System.out.printf("Total SKS sekarang: %d / %d\n", krs.totalSks(), KRS.MAX_SKS);
        System.out.print("Masukkan kode MK: ");
        String kode = sc.nextLine().trim();
        System.out.print("Masukkan nama MK: ");
        String namaMK = sc.nextLine().trim();
        int sks = 0;
        while (true) {
            System.out.print("Masukkan SKS (angka): ");
            String input = sc.nextLine().trim();
            try {
                sks = Integer.parseInt(input);
                if (sks <= 0) {
                    System.out.println("SKS harus > 0.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka valid.");
            }
        }

        MataKuliah mk = new MataKuliah(kode, namaMK, sks);
        if (krs.tambahMK(mk)) {
            System.out.println("Mata kuliah berhasil ditambahkan.");
            System.out.printf("Total SKS sekarang: %d / %d\n", krs.totalSks(), KRS.MAX_SKS);
        } else {
            System.out.println("Gagal menambahkan. Melebihi batas 24 SKS.");
        }
    }
}
