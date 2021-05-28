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
      } else {

      }
    },
    error: function (err) {
      $('.emot').show()
      $('.error-massage').text("Resi tidak ditemukan")
      alertError()
    }
  });
}

function requestKota() {
  $.ajax({
    url: "/api/kotaRaja",
    method: 'get',
    contentType: 'application/json',
    dataType: 'json',
    success: function (res, status, xhr) {
      if (xhr.status == 200 || xhr.status == 201) {
        var s = '<p class="col-sm-12 pilihKota" id="'+res[0].city_id+'">' + res[0].city_name + ", " + res[0].province+'</p>'
        for (var i = 1; i < res.length; i++) {
          if (res[i].type == 'Kabupaten') {
            s += '<p class="col-sm-12 pilihKota" id="'+res[i].city_id+'">' + res[i].city_name + ", " + res[i].province+'</p>'
          } else {
            s += '<p class="col-sm-12 pilihKota" id="'+res[i].city_id+'">' +  res[i].type + " " +  res[i].city_name + ", " + res[i].province+'</p>'
          }
        }
        $('#kotaList').html(s);
        $('#kotaList2').html(s);
        //lempar value
        $('.asal-paket .pilihKota').click(function (){
          $('.asal-paket input').addClass('naik')
          $('.asal-paket input').val($(this).text())
          $('#asalHidden').val(this.id)
        })
        $('.tujuan-paket .pilihKota').click(function (){
          $('.tujuan-paket input').addClass('naik')
          $('.tujuan-paket input').val($(this).text())
          $('#tujuanHidden').val(this.id)
        })
      } else {

      }
    },
    error: function (err) {
      console.log(err)
    }
  });
}

function cekOngkir(asal, tujuan, berat) {
  $('.row-loading').fadeIn('fast');
  $.ajax({
    url: "/api/cost/" + asal + "/" + tujuan + "/" + berat,
    method: 'get',
    contentType: 'application/json',
    dataType: 'json',
    success: function (res, status, xhr) {
      if (xhr.status == 200 || xhr.status == 201) {
        $('.row-loading').fadeOut('fast');
        if (res == 0 || res == "") {
          $('.emot').hide()
          $('.error-massage').text("Maaf layanan tidak tersedia")
          alertError()
        } else {
          var tr = '<tr>' +
            '<td>' + res[0].service + '</td>' +
            '<td>' + berat/1000 + ' kg'+ '</td>' +
            '<td>' + res[0].cost[0].etd + ' Hari'+ '</td>' +
            '<td class="biru">' + 'Rp' + res[0].cost[0].value + '</td>' +
            '</tr>'
          for (var i=1; i<res.length; i++) {
            tr += '<tr>' +
              '<td>' + res[i].service + '</td>' +
              '<td>' + berat/1000 + ' kg'+ '</td>' +
              '<td>' + res[i].cost[0].etd + ' Hari'+ '</td>' +
              '<td class="biru">' + 'Rp' + res[i].cost[0].value + '</td>' +
              '</tr>'
          }
          $('.table-cek-tarif').html(tr)
          $('.table-ongkir').show()

        }
      } else {
        $('.row-loading').fadeOut('fast');
      }
      },
    error: function (err) {
      $('.row-loading').fadeOut('fast');
      $('.emot').hide()
      $('.error-massage').text("Input tidak valid")
      $('.row-alert').fadeIn('slow').delay(500);
      $('.row-alert').fadeIn('slow').delay(500).fadeOut('slow');
    }
  });
}

function cetakPdf(resi) {
  console.log("halo")
  $.ajax({
    url: "/report/" + resi + ".pdf",
    method: 'get',
    contentType: 'application/pdf',
    success: function (res) {
      console.log("weeyy")
      console.log(res)

    },
    error: function (err) {

    }
  });
}
