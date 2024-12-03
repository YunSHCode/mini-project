$(function () {
  $(".floating_menu a.modify_feed").on("click", function (e) {
    const feedId = $(this).data("feedid");
    location.href = `/feed/${feedId}/modify`;
  });
  $(".floating_menu a.delete_feed").on("click", function (e) {
    const feedId = $(this).data("feedid");
    deleteFeed(feedId);
  });
});

function deleteFeed(feedId) {
  if (!feedId) {
    alert("feedId 를 선택해야합니다");
    return;
  }

  const urlString = `/feed/`;

  if (confirm("정말로 삭제하시겠습니까? " + urlString)) {
    $.ajax({
      method: "DELETE",
      uri: urlString,
      dataType: "json",
      data: { feedId: feedId },
    })
      .done(function (result) {
        //AJAX 성공시 실행 코드
        location.reload(true);
        console.log(result);
      })
      .fail(function (data, textStatus, errorThrown) {
        console.log("fail in get addr");
        console.log(data);
      });
  }
}