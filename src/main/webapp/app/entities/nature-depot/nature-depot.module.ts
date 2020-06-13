import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { NatureDepotComponent } from './nature-depot.component';
import { NatureDepotDetailComponent } from './nature-depot-detail.component';
import { NatureDepotUpdateComponent } from './nature-depot-update.component';
import { NatureDepotDeleteDialogComponent } from './nature-depot-delete-dialog.component';
import { natureDepotRoute } from './nature-depot.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(natureDepotRoute)],
  declarations: [NatureDepotComponent, NatureDepotDetailComponent, NatureDepotUpdateComponent, NatureDepotDeleteDialogComponent],
  entryComponents: [NatureDepotDeleteDialogComponent],
})
export class EtatregNatureDepotModule {}
