function cekResi(resi) {
  $.ajax({
    url: "/api/transaksi/resi/" + resi,
    method: 'get',
    contentType: 'application/json',
    dataType: 'json',
    success: function (res, status, xhr) {
      if (xhr.status == 200 || xhr.status == 201) {
        $('.resi').text(res.resi)
        $('.tgl').text(res.tanggalTransaksi)
        $('.layanan').text(res.kategoriLayanan)
        $('.asal').text(res.cityName + ", " + res.provinceName)
        $('.tujuan').text(res.cityNamePenerima + ", " + res.provinceNamePenerima)
        $('.status').text(res.statusDelivery)
        $('.kurir').text(res.namaKurir)
        $('.penerima').text(res.penerimaPaket)
        $('.tglSampai').text(res.tanggalSampai)
        $('.gambar-lacak').css('display', 'none')
        $('.table-resi').css('display', 'block')
        console.log(res)
      } else {

      }
    },
    error: function (err) {
      $('.row-alert').fadeIn('slow').delay(500);
      $('.row-alert').fadeIn('slow').delay(500).fadeOut('slow');
    }
  });
}
