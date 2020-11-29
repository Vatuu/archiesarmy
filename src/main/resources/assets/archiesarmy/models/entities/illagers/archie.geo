{
	"format_version": "1.12.0",
	"minecraft:geometry": [
		{
			"description": {
				"identifier": "geometry.archiesarmy.entities.archie",
				"texture_width": 64,
				"texture_height": 64,
				"visible_bounds_width": 3,
				"visible_bounds_height": 3,
				"visible_bounds_offset": [0, 0, 0]
			},
			"bones": [
				{
					"name": "root",
					"pivot": [0, 0, 0]
				},
				{
					"name": "body",
					"parent": "root",
					"pivot": [0, 6, 0],
					"cubes": [
						{"origin": [-3, 6, -2], "size": [6, 9, 3], "uv": [0, 30]}
					]
				},
				{
					"name": "coat",
					"parent": "body",
					"pivot": [0, 15, 1],
					"cubes": [
						{"origin": [-3, 1, -2], "size": [6, 14, 3], "inflate": 0.5, "uv": [0, 13]}
					]
				},
				{
					"name": "amulet",
					"parent": "body",
					"pivot": [0, 12, -2],
					"cubes": [
						{"origin": [-1, 10, -3], "size": [2, 2, 1], "uv": [0, 3]}
					]
				},
				{
					"name": "head",
					"parent": "body",
					"pivot": [0, 15, 0],
					"cubes": [
						{"origin": [-6, 15, -3], "size": [12, 7, 6], "uv": [0, 0]}
					]
				},
				{
					"name": "nose",
					"parent": "head",
					"pivot": [0, 17, -3],
					"cubes": [
						{"origin": [-1, 14, -4], "size": [2, 4, 1], "uv": [34, 26]}
					]
				},
				{
					"name": "hat",
					"parent": "head",
					"pivot": [0, 22, 0],
					"cubes": [
						{"origin": [-2, 22.01, -2], "size": [4, 11, 4], "uv": [18, 18]}
					]
				},
				{
					"name": "eyebrow_left",
					"parent": "head",
					"pivot": [3, 19, -3],
					"cubes": [
						{"origin": [1, 18, -3.01], "size": [4, 3, 0], "inflate": 0.01, "uv": [34, 23]}
					]
				},
				{
					"name": "eyebrow_right",
					"parent": "head",
					"pivot": [-3, 19, -3],
					"cubes": [
						{"origin": [-5, 18, -3.01], "size": [4, 3, 0], "inflate": 0.01, "uv": [34, 20]}
					]
				},
				{
					"name": "eye_left",
					"parent": "head",
					"pivot": [-2, 15, 2],
					"cubes": [
						{"origin": [1, 17, -3], "size": [3, 1, 0], "inflate": 0.01, "uv": [24, 17]}
					]
				},
				{
					"name": "eye_right",
					"parent": "head",
					"pivot": [-2, 15, 2],
					"cubes": [
						{"origin": [-4, 17, -3], "size": [3, 1, 0], "inflate": 0.01, "uv": [18, 17]}
					]
				},
				{
					"name": "left_leg",
					"parent": "body",
					"pivot": [1, 6, 0],
					"cubes": [
						{"origin": [0, 3, -2], "size": [3, 3, 3], "uv": [22, 34]},
						{"origin": [1, 0, -1], "size": [1, 3, 1], "uv": [18, 18]},
						{"origin": [1, -0.01, -3], "size": [1, 0, 3], "inflate": 0.01, "uv": [0, 0]}
					]
				},
				{
					"name": "right_leg",
					"parent": "body",
					"pivot": [-1, 6, 0],
					"cubes": [
						{"origin": [-3, 3, -2], "size": [3, 3, 3], "uv": [33, 14]},
						{"origin": [-2, 0, -1], "size": [1, 3, 1], "uv": [18, 18]},
						{"origin": [-2, -0.01, -3], "size": [1, 0, 3], "inflate": 0.01, "uv": [0, 0]}
					]
				},
				{
					"name": "left_arm",
					"parent": "body",
					"pivot": [3, 15, 0],
					"cubes": [
						{"origin": [3, 12.01, -2], "size": [4, 3, 3], "uv": [31, 31]},
						{"origin": [3, 14, -1], "size": [8, 1, 1], "uv": [18, 15]}
					]
				},
				{
					"name": "staff",
					"parent": "left_arm",
					"pivot": [10, 15, -1],
					"cubes": [
						{"origin": [10, 3, -2], "size": [1, 18, 1], "uv": [18, 33]},
						{"origin": [9, 21, -3], "size": [3, 1, 3], "uv": [33, 10]}
					]
				},
				{
					"name": "KYOOB",
					"parent": "staff",
					"pivot": [10.5, 24.5, -1.5]
				},
				{
					"name": "right_arm",
					"parent": "body",
					"pivot": [-3, 15, 0],
					"cubes": [
						{"origin": [-7, 12.01, -2], "size": [4, 3, 3], "uv": [30, 0]},
						{"origin": [-11, 14, -1], "size": [8, 1, 1], "uv": [15, 13]}
					]
				}
			]
		}
	]
}