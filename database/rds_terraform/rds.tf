module "revtest_rds" {
  source              = "modules/terraform-aws-rds-mysql"
  name                = "revtest"
  username            = "root"
  password            = "Changem3"
  database            = "pipefish"
  db_subnets         = ["${var.private_subnets[0]}","${var.private_subnets[1]}"]
  trusted_cidr_blocks = [
    "${var.public_subnet_cidr[0]}",
    "${var.public_subnet_cidr[1]}"
  ]
}