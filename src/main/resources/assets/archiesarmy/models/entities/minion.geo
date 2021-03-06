{
	"format_version": "1.16.4",
	"minecraft:geometry": [
		{
			"description": {
				"identifier": "geometry.geometry.anime_mash_up.minion",
				"texture_width": 128,
				"texture_height": 128,
				"visible_bounds_width": 5,
				"visible_bounds_height": 4.5,
				"visible_bounds_offset": [0, 0.75, 0]
			},
			"bones": [
				{
					"name": "main",
					"pivot": [0, 0, 0]
				},
				{
					"name": "upperleftleg",
					"parent": "main",
					"pivot": [6.58333, 10, 0.33333],
					"rotation": [4, -20, -11],
					"cubes": [
						{"origin": [4.5, 6, -1.5], "size": [4, 5, 4], "uv": [16, 79]},
						{"origin": [5, 4, -1], "size": [3, 2, 3], "uv": [49, 64]}
					]
				},
				{
					"name": "lowerleftleg",
					"parent": "upperleftleg",
					"pivot": [6.5, 5, 0],
					"rotation": [0, 0, 10],
					"cubes": [
						{"origin": [4.5, 2, -1.5], "size": [4, 3, 4], "uv": [82, 49]},
						{"origin": [4, 0, -3.5], "size": [5, 2, 6], "uv": [14, 69]}
					]
				},
				{
					"name": "upperrightleg",
					"parent": "main",
					"pivot": [-6.58333, 10, 0.33333],
					"rotation": [4, 20, 11],
					"cubes": [
						{"origin": [-8.5, 6, -1.5], "size": [4, 5, 4], "uv": [77, 57]},
						{"origin": [-8, 4, -1], "size": [3, 2, 3], "uv": [34, 57]}
					]
				},
				{
					"name": "lowerrightleg",
					"parent": "upperrightleg",
					"pivot": [-6.5, 5, 0],
					"rotation": [0, 0, -10],
					"cubes": [
						{"origin": [-8.5, 2, -1.5], "size": [4, 3, 4], "uv": [79, 81]},
						{"origin": [-9, 0, -3.5], "size": [5, 2, 6], "uv": [64, 34]}
					]
				},
				{
					"name": "body",
					"parent": "main",
					"pivot": [0, 10, 0],
					"cubes": [
						{"origin": [-7.5, 11, -2], "size": [15, 12, 8], "uv": [0, 0]},
						{"origin": [-5.5, 22.82459, -2.0493], "size": [11, 4, 8], "pivot": [0, 24.32459, 0.9507], "rotation": [2, 0, 0], "uv": [34, 34]},
						{"origin": [-2.5, 6.25, -3], "size": [5, 5, 9], "uv": [39, 11]},
						{"origin": [1.31884, 8.75024, -2], "size": [7, 3, 8], "pivot": [4.81884, 8.75024, 1], "rotation": [0, 0, -40], "uv": [22, 46]},
						{"origin": [-8.31884, 8.75024, -2], "size": [7, 3, 8], "pivot": [-4.81884, 8.75024, 1], "rotation": [0, 0, 40], "uv": [0, 40]}
					]
				},
				{
					"name": "upperleftarm",
					"parent": "body",
					"pivot": [7.8172, 16.46317, 1.34972],
					"rotation": [0, 5, -55],
					"cubes": [
						{"origin": [6.23386, 9.46317, 0.01639], "size": [3, 3, 3], "uv": [82, 66]},
						{"origin": [5.23386, 12.46317, -0.98361], "size": [5, 5, 5], "uv": [34, 64]}
					]
				},
				{
					"name": "lowerleftarm",
					"parent": "upperleftarm",
					"pivot": [7.86575, 9.70951, 1.01122],
					"rotation": [-70, 15, 0],
					"cubes": [
						{"origin": [5.11882, 3.77812, -0.98361], "size": [5, 7, 5], "uv": [19, 57]}
					]
				},
				{
					"name": "leftfist",
					"parent": "lowerleftarm",
					"pivot": [7.61882, 3.27812, 1.51639],
					"cubes": [
						{"origin": [5.61882, -0.22188, -0.48361], "size": [4, 4, 4], "uv": [63, 81], "mirror": true}
					]
				},
				{
					"name": "engine",
					"parent": "body",
					"pivot": [0, 14.86318, 6.5076],
					"rotation": [10, 0, 0],
					"cubes": [
						{"origin": [-2, 19.91318, 5.0076], "size": [4, 2, 4], "uv": [80, 34]},
						{"origin": [-2.5, 16.91318, 4.5076], "size": [5, 3, 5], "uv": [31, 75]},
						{"origin": [-3, 11.91318, 4.0076], "size": [6, 5, 6], "uv": [0, 51]},
						{"origin": [-1, 9.16318, 6.0076], "size": [2, 1, 2], "inflate": 0.25, "uv": [0, 40]},
						{"origin": [-1, 7.91318, 6.0076], "size": [2, 1, 2], "uv": [0, 3]},
						{"origin": [-3, 10.66318, 4.0076], "size": [6, 1, 6], "inflate": 0.25, "uv": [61, 19]}
					]
				},
				{
					"name": "facecovering",
					"parent": "body",
					"pivot": [4.83721, 8.86064, -2],
					"cubes": [
						{"origin": [7.26644, 19.5, -1.57697], "size": [1, 1, 1], "pivot": [7.01644, 23, -0.07697], "rotation": [0, -15, 0], "uv": [35, 40]},
						{"origin": [7.01644, 19, -2.07697], "size": [1, 2, 2], "pivot": [7.01644, 23, -0.07697], "rotation": [0, -15, 0], "uv": [54, 69]},
						{"origin": [8.04289, 12.5, -0.47474], "size": [1, 1, 1], "pivot": [7.79289, 24, -1.97474], "rotation": [0, -15, 0], "uv": [31, 40]},
						{"origin": [7.79289, 12, -0.97474], "size": [1, 2, 2], "pivot": [7.79289, 24, -1.97474], "rotation": [0, -15, 0], "uv": [64, 34]},
						{"origin": [-8.5, 10, -5.75], "size": [17, 5, 5], "uv": [0, 20]},
						{"origin": [-8, 15, -5], "size": [16, 5, 3], "uv": [38, 0]},
						{"origin": [-8.5, 18, -5.75], "size": [17, 6, 4], "uv": [0, 30]},
						{"origin": [-6.5, 23.55, -4.75], "size": [13, 4, 3], "pivot": [0.5, 24.55, -3.25], "rotation": [-5, 0, 0], "uv": [41, 27]},
						{"origin": [-0.5, 26.53853, -0.76142], "size": [1, 1, 1], "pivot": [-0.5, 24.28853, -0.26142], "rotation": [-5, 0, 0], "uv": [0, 32]},
						{"origin": [-0.5, 5.87312, -1.68002], "size": [1, 1, 1], "pivot": [-0.5, 8.12312, -0.68002], "rotation": [5, 0, 0], "uv": [0, 30]},
						{"origin": [-1, 26.28853, -1.76142], "size": [2, 1, 3], "pivot": [-0.5, 24.28853, -0.26142], "rotation": [-5, 0, 0], "uv": [79, 21]},
						{"origin": [-1, 6.12312, -2.18002], "size": [2, 1, 2], "pivot": [-0.5, 8.12312, -0.68002], "rotation": [5, 0, 0], "uv": [0, 0]},
						{"origin": [-2, 14.22474, -7.35711], "size": [4, 7, 4], "pivot": [0, 17.72474, -5.35711], "rotation": [-20, 0, 0], "uv": [0, 74]},
						{"origin": [-4, 15.22474, -6.35711], "size": [2, 2, 3], "pivot": [-2, 17.72474, -5.35711], "rotation": [-20, 0, 0], "uv": [0, 85]},
						{"origin": [2, 15.22474, -6.35711], "size": [2, 2, 3], "pivot": [2, 17.72474, -5.35711], "rotation": [-20, 0, 0], "uv": [32, 83]},
						{"origin": [-7, 18.75, -6.5], "size": [5, 3, 1], "pivot": [-4.5, 20.25, -6], "rotation": [0, 0, 2], "uv": [76, 0]},
						{"origin": [2, 18.75, -6.5], "size": [5, 3, 1], "pivot": [4.5, 20.25, -6], "rotation": [0, 0, -2], "uv": [67, 57]},
						{"origin": [-8.01644, 19, -2.07697], "size": [1, 2, 2], "pivot": [-7.01644, 23, -0.07697], "rotation": [0, 15, 0], "uv": [0, 51]},
						{"origin": [-8.26644, 19.5, -1.57697], "size": [1, 1, 1], "pivot": [-7.01644, 23, -0.07697], "rotation": [0, 15, 0], "uv": [4, 6]},
						{"origin": [-9.04289, 12.5, -0.47474], "size": [1, 1, 1], "pivot": [-7.79289, 24, -1.97474], "rotation": [0, 15, 0], "uv": [0, 6]},
						{"origin": [-8.79289, 12, -0.97474], "size": [1, 2, 2], "pivot": [-7.79289, 24, -1.97474], "rotation": [0, 15, 0], "uv": [0, 43]},
						{"origin": [1.83721, 8.36064, -5], "size": [6, 4, 3], "pivot": [4.83721, 8.86064, -2], "rotation": [0, 0, -40], "uv": [77, 42]},
						{"origin": [-7.83721, 8.36064, -5], "size": [6, 4, 3], "pivot": [-4.83721, 8.86064, -2], "rotation": [0, 0, 40], "uv": [75, 5]}
					]
				},
				{
					"name": "jaw",
					"parent": "facecovering",
					"pivot": [-3.5, 9, -3.75],
					"rotation": [-15, 0, 0],
					"cubes": [
						{"origin": [-3, 11, -7], "size": [6, 2, 5], "pivot": [0, 8.5, -4.5], "rotation": [27.5, 0, 0], "uv": [49, 73]},
						{"origin": [2.5, 9, -6.75], "size": [2, 5, 0], "pivot": [3.5, 11, -6.75], "rotation": [24.03363, -7.0977, 15.48731], "uv": [18, 51]},
						{"origin": [-3, 6, -7], "size": [6, 5, 5], "pivot": [0, 8.5, -4.5], "rotation": [27.5, 0, 0], "uv": [58, 8]},
						{"origin": [-4.5, 9, -6.75], "size": [2, 5, 0], "pivot": [-3.5, 11, -6.75], "rotation": [24.03363, 7.0977, -15.48731], "uv": [0, 20]}
					]
				},
				{
					"name": "headthing",
					"parent": "facecovering",
					"pivot": [-1.62635, 23.11924, -5]
				},
				{
					"name": "lefthorn",
					"parent": "headthing",
					"pivot": [6.09839, 25.23659, -1.55],
					"rotation": [3.6567, -8.37769, -1.64573],
					"cubes": [
						{"origin": [4.22635, 24.66924, -4.05], "size": [5, 6, 5], "pivot": [9.52635, 26.91924, -2.05], "rotation": [0, 0, -39], "uv": [62, 46]},
						{"origin": [5.69111, 30.17022, -3.58595], "size": [6, 4, 4], "pivot": [11.20719, 30.66613, -2.05], "rotation": [0, 0, -71.5], "uv": [73, 26]},
						{"origin": [5.54626, 35.13247, -2.94841], "size": [6, 3, 3], "pivot": [10.09839, 35.23659, -1.55], "rotation": [0, 0, -106.5], "uv": [79, 15]}
					]
				},
				{
					"name": "righthorn",
					"parent": "headthing",
					"pivot": [-6.09839, 25.23659, -1.55],
					"rotation": [3.6567, 8.37769, 1.64573],
					"cubes": [
						{"origin": [-9.22635, 24.66924, -4.05], "size": [5, 6, 5], "pivot": [-9.52635, 26.91924, -2.05], "rotation": [0, 0, 39], "uv": [62, 62]},
						{"origin": [-11.69111, 30.17022, -3.58595], "size": [6, 4, 4], "pivot": [-11.20719, 30.66613, -2.05], "rotation": [0, 0, 71.5], "uv": [71, 73]},
						{"origin": [-11.54626, 35.13247, -2.94841], "size": [6, 3, 3], "pivot": [-10.09839, 35.23659, -1.55], "rotation": [0, 0, 106.5], "uv": [44, 46]}
					]
				},
				{
					"name": "upperrightarm",
					"parent": "body",
					"pivot": [-7.8172, 16.46317, 1.34972],
					"rotation": [0, -5, 55],
					"cubes": [
						{"origin": [-9.23386, 9.46317, 0.01639], "size": [3, 3, 3], "uv": [22, 40]},
						{"origin": [-10.23386, 12.46317, -0.98361], "size": [5, 5, 5], "uv": [0, 64], "mirror": true}
					]
				},
				{
					"name": "lowerrightarm",
					"parent": "upperrightarm",
					"pivot": [-7.86575, 9.70951, 1.01122],
					"rotation": [-70, -15, 0],
					"cubes": [
						{"origin": [-10.11882, 3.77812, -0.98361], "size": [5, 7, 5], "uv": [47, 52], "mirror": true}
					]
				},
				{
					"name": "rightfist",
					"parent": "lowerrightarm",
					"pivot": [-7.61882, 3.27812, 1.51639],
					"cubes": [
						{"origin": [-9.61882, -0.22188, -0.48361], "size": [4, 4, 4], "uv": [47, 80], "mirror": true}
					]
				}
			]
		}
	]
}