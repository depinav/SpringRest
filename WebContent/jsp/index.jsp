<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title></title>
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/foundation/4.3.2/css/foundation.min.css" />
</head>
<body>

	<input type="text" />
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody class="list">
		</tbody>
	</table>
	
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script>
		
		$(document).ready(function() {
			
			$.getJSON( "/SpringRest/api/personlist", function(data) {
				
				var items = [];
				$.each(data, function(key, value) {
					items.push("<tr> <th> " + (key + 3) + "</th> <th> " + value.name + "</th></tr>");
				});
				
				var itemsBody = items.join("");
				
				$(".list").html(itemsBody);
			});
		});
	</script>
</body>
</html>