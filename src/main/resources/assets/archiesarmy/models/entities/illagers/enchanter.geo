{
	"format_version": "1.16.4",
	"minecraft:geometry": [
		{
			"description": {
				"identifier": "geometry.unknown",
				"texture_width": 128,
				"texture_height": 64,
				"visible_bounds_width": 2,
				"visible_bounds_height": 3,
				"visible_bounds_offset": [0, 0, 0]
			},
			"bones": [
				{
					"name": "root",
					"pivot": [0, 0, 0]
				},
				{
					"name": "head",
					"parent": "root",
					"pivot": [0, 24, 0],
					"cubes": [
						{"origin": [-4, 24, -4], "size": [8, 10, 8], "uv": [0, 0]},
						{"origin": [-5, 30, -5], "size": [10, 8, 10], "uv": [0, 18]}
					]
				},
				{
					"name": "nose",
					"parent": "head",
					"pivot": [0, 26, -4],
					"cubes": [
						{"origin": [-1, 23, -6], "size": [2, 4, 2], "uv": [24, 0]}
					]
				},
				{
					"name": "body",
					"parent": "root",
					"pivot": [0, 20, 0],
					"cubes": [
						{"origin": [-4, 12, -3], "size": [8, 12, 6], "uv": [32, 0]},
						{"origin": [-4, 5, -3], "size": [8, 19, 6], "inflate": 0.5, "uv": [0, 36]}
					]
				},
				{
					"name": "left_leg",
					"parent": "body",
					"pivot": [2, 12, 0],
					"cubes": [
						{"origin": [0, 0, -2], "size": [4, 12, 4], "uv": [40, 18], "mirror": true}
					]
				},
				{
					"name": "right_leg",
					"parent": "body",
					"pivot": [-2, 12, 0],
					"cubes": [
						{"origin": [-4, 0, -2], "size": [4, 12, 4], "uv": [40, 18]}
					]
				},
				{
					"name": "left_arm",
					"parent": "body",
					"pivot": [4, 24, 0],
					"cubes": [
						{"origin": [4, 12, -2], "size": [4, 12, 4], "uv": [56, 18]}
					]
				},
				{
					"name": "right_arm",
					"parent": "body",
					"pivot": [-4, 24, 0],
					"cubes": [
						{"origin": [-8, 12, -2], "size": [4, 12, 4], "uv": [56, 18], "mirror": true}
					]
				},
				{
					"name": "cape",
					"parent": "body",
					"pivot": [0, 24, 3],
					"rotation": [10, 0, 0],
					"cubes": [
						{"origin": [-6, 1, 3], "size": [12, 23, 1], "uv": [28, 36]}
					]
				},
				{
					"name": "villager_arms",
					"parent": "body",
					"pivot": [0, 21, -1],
					"rotation": [-45, 0, 0],
					"cubes": [
						{"origin": [4, 15, -3], "size": [4, 8, 4], "uv": [54, 34], "mirror": true},
						{"origin": [-8, 15, -3], "size": [4, 8, 4], "uv": [54, 34]},
						{"origin": [-4, 15, -3], "size": [8, 4, 4], "uv": [54, 46]}
					]
				}
			]
		}
	]
}