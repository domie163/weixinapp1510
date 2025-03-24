<template>
	<view class="diy_details diy_div_dietary_health_preservation">
		<view v-if="Object.keys(obj).length!==0" class="warp">
			<view class="container">
				<view class="row">
					<view v-if="$check_field('get','health_title')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>养生标题:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["health_title"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','type_of_health_preservation')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>养生类型:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["type_of_health_preservation"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','cover_photo')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>封面图片:</span>
						</view>
							<view class="diy_field diy_img">
							<image style="width:100%;height:auto;" :src="$fullUrl(obj['cover_photo'])" mode="widthFix"></image>
						</view>
						</view>
					<view v-if="$check_field('get','release_date')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>发布日期:</span>
						</view>
							<view class="diy_field diy_date">
							<text>
								{{ $toTime(obj["release_date"],"yyyy-MM-dd") }}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','health_preservation_methods')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>养生方法:</span>
						</view>
							<view class="diy_field diy_desc">
							<text>
								{{ obj["health_preservation_methods"] }}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','health_preservation_plan')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>养生方案:</span>
						</view>
							<view class="diy_field diy_desc">
							<text>
								{{ obj["health_preservation_plan"] }}
							</text>
						</view>
						</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import mixin from "@/libs/mixins/component.js";
	export default {
		mixins: [mixin],
		props: {
			obj_goods: {
				type: Object,
				default(){
					return {}
				}
			},
			query:{
				type: Object,
				default(){
					return {
						dietary_health_preservation_id: 0
					}
				}
			}
		},
		data() {
			return {
				obj_goods_type: {},
				obj: {},
									}
		},
		methods: {
			async get_obj_goods_type() {
				var res = await this.$get("~/api/goods_type/get_obj", {
					name: this.obj_goods.type
				})

				if (res.result.obj) {
					this.obj_goods_type = res.result.obj
				} else {
					console.log("没有请求到商品分类");
				}
			},
			async get_obj_by_goods() {
				var {
					source_table,
					source_field
				} = this.obj_goods_type
				var {
					source_id
				} = this.obj_goods
				var query = {}
				query[source_field] = source_id
				this.$get("~/api/" + source_table + "/get_obj", {}, (res) => {
					if (res.result.obj) {
						this.obj = res.result.obj
					} else {
						console.log("没有请求到商品分类");
					}
				})
			},
			async get_obj_by_id(){
				var res = await this.$get("~/api/dietary_health_preservation/get_obj", {
					dietary_health_preservation_id:this.query.dietary_health_preservation_id
				})

				if (res.result && res.result.obj) {
					this.obj = res.result.obj
				} else {
					console.log("没有请求到商品分类");
				}
			},
								},
		created() {
								},
		async onLoad(){
			if (Object.keys(this.obj_goods).length !== 0) {
				await this.get_obj_goods_type();
				await this.get_obj_by_goods();
			} else if (this.query["dietary_health_preservation_id"] !==0) {
				await this.get_obj_by_id();
			}
		}
	}
</script>

<style scoped>
	.div_dietary_health_preservation_details {}

	.details {
		background-color: #fff;
		margin-bottom: 0.5rem;
		padding: 1rem;
		font-size: 10px;
	}

	.item {
		display: flex;
		padding: 0.2rem 0;
		border-bottom: 1px solid #eee;
	}

	.left_text {
		flex: 0 0 25%;
	}

	.right_text {
		flex: 0 0 75%;
	}
	.edit_nav{
		text-align: center;
		background-color: #fff;
		padding: 0.3rem;
		margin: 0.1rem 1rem;
		border: 1px solid #eee;
		border-radius: 0.5rem;
	}
</style>
