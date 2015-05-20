CREATE TABLE "public"."referenceno" (
	"id" bigserial NOT NULL,
	"requestnum" int4,
	"created_by" varchar NOT NULL COLLATE "default",
	"created_date" timestamp(6) NOT NULL,
	"last_modified_by" varchar COLLATE "default",
	"last_modified_date" timestamp(6) NULL,
	PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);


CREATE TABLE "public"."jobs" (
	"id" bigserial NOT NULL,
	"requestnum" int4,
	"daterequested" timestamp NULL,
	"title" varchar,
	"description" varchar,
	"denied" bool,
	"datedenied" timestamp NULL,
	"reasondenied" varchar,
	"pending" bool,
	"reasonpending" varchar,
	"personofficeconcerned" varchar,
	"approved" bool,
	"dateapproved" timestamp NULL,
	"implementingentity" varchar,
	"expecteddatecompletion" timestamp NULL,
	"approveremarks" varchar,
	"ongoing" bool,
	"datestarted" timestamp NULL,
	"ongoingexpecteddatecompletion" timestamp NULL,
	"ongoingremarks" varchar,
	"finished" bool,
	"datefinished" timestamp NULL,
	"finishedremarks" varchar,
	"created_by" varchar NOT NULL COLLATE "default",
	"created_date" timestamp(6) NOT NULL,
	"last_modified_by" varchar COLLATE "default",
	"last_modified_date" timestamp(6) NULL,
	PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);